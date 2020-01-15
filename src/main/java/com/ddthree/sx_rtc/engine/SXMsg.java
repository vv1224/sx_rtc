package com.ddthree.sx_rtc.engine;

import lombok.Data;

import javax.annotation.Nonnull;

/**
 * 请填写类的描述
 * <ul>
 * <li>[2020/1/14 19:53]XXX：初始创建</li>
 * </ul>
 *
 * @author XXX
 */
@Data
public class SXMsg {
    @Nonnull
    private final String id = IDGenerator.genMsgId();

    @Nonnull
    private final String addrFrom;
    @Nonnull
    private final String addrTo;
    @Nonnull
    private final String content;
}
