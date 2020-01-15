package com.ddthree.sx_rtc.engine;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

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
     * 用户id => 消息缓存
     */
    private final ConcurrentHashMap<String, MsgHolder> holders = new ConcurrentHashMap<>();


    @Override
    public void pushMsg(@Nonnull SXMsg[] msgs) {
        for (SXMsg msg : msgs) {
            if (holders.containsKey(msg.getAddrFrom())) {
                MsgHolder holder = holders.get(msg.getAddrTo());
                if (holder != null) {
                    holder.putMsg(msg);
                }
            }
        }
    }

    @Nonnull
    @Override
    public SXMsg[] pullMsg(@Nonnull String addr, long millis) {
        MsgHolder holder = holders.get(addr);
        if (holder == null) {
            throw new IllegalStateException("用户未登录");
        } else {
            return holder.getMsg(millis);
        }
    }

    @Override
    public void login(@Nonnull String addr) {
        MsgHolder holder = holders.computeIfAbsent(addr, s -> new MsgHolder());
        holder.setLastGet(System.currentTimeMillis());
    }

    @Override
    public void logout(@Nonnull String addr) {
        holders.remove(addr);
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

        private final LinkedList<SXMsg> msgs = new LinkedList<>();
        private final Semaphore semaphore = new Semaphore(0);

        @Getter
        @Setter
        private long lastGet = System.currentTimeMillis();

        final void putMsg(@Nonnull SXMsg msg) {
            msgs.add(msg);
            if (semaphore.availablePermits() <= 0) {
                semaphore.release();
            }
        }

        @Nonnull
        public final SXMsg[] getMsg(long millis) {
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
