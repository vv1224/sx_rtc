package com.ddthree.sx_rtc.dao.entity;

import com.ddthree.sx_rtc.utils.XEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 请填写类的描述
 * <ul>
 * <li>[2020/1/15 12:51]XXX：初始创建</li>
 * </ul>
 *
 * @author XXX
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends XEntity {
    private String username;
    private String password;
    private String gender;
}
