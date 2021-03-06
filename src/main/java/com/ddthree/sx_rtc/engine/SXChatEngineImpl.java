package com.ddthree.sx_rtc.engine;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 聊天引擎
 * <ul>
 * <li>[2020/1/14 19:55]XXX：初始创建</li>
 * </ul>
 *
 * @author XXX
 */
@Component
public class SXChatEngineImpl implements SXChatEngine {
    /**
     * MAP读写锁
     */
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    /**
     * 用户id => 消息缓存
     */
    private final HashMap<String, MsgHolder> holders = new HashMap<>();

    @Override
    public void pushMsg(@Nonnull SXMsg msg) {
        rwLock.readLock().lock();
        try {
            MsgHolder waiter = holders.get(msg.getUserTo());
            if (waiter == null) {
                throw new IllegalStateException("用户未登录");
            } else {
                waiter.putMsg(msg);
            }
        } finally {
            rwLock.readLock().unlock();
        }
    }

    @Nonnull
    @Override
    public SXMsg[] pullMsg(@Nonnull SXUser user, long millis) {
        rwLock.readLock().lock();
        try {
            MsgHolder waiter = holders.get(user.getId());
            if (waiter == null) {
                throw new IllegalStateException("用户未登录");
            } else {
                return waiter.getMsg(millis);
            }
        } finally {
            rwLock.readLock().unlock();
        }
    }

    @Override
    public void login(@Nonnull SXUser user) {
        rwLock.writeLock().lock();
        try {
            MsgHolder waiter = holders.get(user.getId());
            if (waiter == null) {
                waiter = new MsgHolder(user);
                holders.put(user.getId(), waiter);
            } else {
                waiter.setLastGet(System.currentTimeMillis());
            }
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    @Override
    public void logout(@Nonnull SXUser user) {
        rwLock.writeLock().lock();
        try {
            holders.remove(user.getId());
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    /**
     * 每个用户的缓存消息池。
     * <ul>
     *     <li>没有消息时，调用getMsg方法会阻塞，直到有消息到来或者等待时间耗尽</li>
     *     <li>有消息时，调用getMsg方法会立即返回</li>
     * </ul>
     */
    public static class MsgHolder {
        private static final SXMsg[] ARR0 = new SXMsg[0];

        @Getter
        private final SXUser user;
        private final LinkedList<SXMsg> msgs = new LinkedList<>();
        private final Semaphore semaphore = new Semaphore(0);

        @Getter
        @Setter
        private long lastGet = System.currentTimeMillis();

        public MsgHolder(SXUser user) {
            this.user = user;
        }

        synchronized final void putMsg(@Nonnull SXMsg msg) {
            msgs.add(msg);
            if (semaphore.availablePermits() <= 0) {
                semaphore.release();
            }
        }

        @Nonnull
        public synchronized final SXMsg[] getMsg(long millis) {
            try {
                if (millis > 0 && semaphore.tryAcquire(millis, TimeUnit.MILLISECONDS)) {
                    SXMsg[] result = msgs.toArray(ARR0);
                    lastGet = System.currentTimeMillis();
                    msgs.clear();
                    return result;
                } else {
                    return ARR0;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
