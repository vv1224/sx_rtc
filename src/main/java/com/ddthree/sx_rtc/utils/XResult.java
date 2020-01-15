package com.ddthree.sx_rtc.utils;

import lombok.Getter;

@Getter
public class XResult<T> {
    /**
     * 响应的代码，常用的有
     * <ol>
     * <li>HttpServletResponse.SC_OK=200 (正常返回)</li>
     * <li>HttpServletResponse.SC_BAD_REQUEST=400 (客户端错误)</li>
     * <li>HttpServletResponse.SC_UNAUTHORIZED=401 (未通过认证)</li>
     * <li>HttpServletResponse.SC_FORBIDDEN=403 (没有权限)</li>
     * <li>HttpServletResponse.SC_INTERNAL_SERVER_ERROR=500 (服务器内部错误)</li>
     * </ol>
     */
    private final int code;

    /**
     * 响应的异常信息，只有当XController运行异常时才存在
     */
    private final String info;

    /**
     * 响应的数据，只有当XController运行正常时才存在
     */
    private final T data;

    private XResult(int code, String info, T data) {
        this.code = code;
        this.info = info;
        this.data = data;
    }

    public static <S> XResult<S> success(S data) {
        return new XResult<>(200, null, data);
    }

    public static <S> XResult<S> failure(int code, String info) {
        return new XResult<>(code, info, null);
    }
}
