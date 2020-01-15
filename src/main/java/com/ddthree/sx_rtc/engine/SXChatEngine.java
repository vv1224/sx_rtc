package com.ddthree.sx_rtc.engine;

import javax.annotation.Nonnull;

/**
 * 请填写类的描述
 * <ul>
 * <li>[2020/1/14 19:51]XXX：初始创建</li>
 * </ul>
 *
 * @author XXX
 */
public interface SXChatEngine {

    void pushMsg(@Nonnull SXMsg[] msg);

    @Nonnull
    SXMsg[] pullMsg(@Nonnull String addr, long millis);

    void login(@Nonnull String addr);

    void logout(@Nonnull String addr);
}
