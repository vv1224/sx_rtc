package com.ddthree.sx_rtc.utils;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
public abstract class XEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long createUser;
    private Long updateUser;
    private Date createTime;
    private Date updateTime;
}
