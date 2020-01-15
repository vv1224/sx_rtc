package com.ddthree.sx_rtc.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class SXException extends RuntimeException implements Serializable {
    private final int code;
    private final String info;

    public SXException(int code, String info) {
        this.code = code;
        this.info = info;
    }
}
