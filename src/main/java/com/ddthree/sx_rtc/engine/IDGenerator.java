package com.ddthree.sx_rtc.engine;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 请填写类的描述
 * <ul>
 * <li>[2020/1/14 20:32]XXX：初始创建</li>
 * </ul>
 *
 * @author XXX
 */
public class IDGenerator {
    private static final AtomicInteger COUNTER = new AtomicInteger(0);

    public static String genMsgId() {
        return String.valueOf(System.currentTimeMillis()) + COUNTER.getAndIncrement() % 1000000;
    }

    public static String genUserId() {
        return "U" + System.currentTimeMillis() + COUNTER.getAndIncrement() % 1000000;
    }
}
