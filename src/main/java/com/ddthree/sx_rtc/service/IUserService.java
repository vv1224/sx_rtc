package com.ddthree.sx_rtc.service;


import javax.annotation.Nonnull;

/**
 * 请填写类的描述
 * <ul>
 * <li>[2020/1/15 12:56]XXX：初始创建</li>
 * </ul>
 *
 * @author XXX
 */
public interface IUserService {

    @Nonnull
    String userLogin(@Nonnull String username, @Nonnull String password, @Nonnull String platform);
}
