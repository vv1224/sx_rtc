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
public class SXUser {

    @Nonnull
    private final String id = IDGenerator.genUserId();
    private final String nick;
}
