package com.ddthree.sx_rtc.exception;

import com.ddthree.sx_rtc.utils.XResult;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @author xxx
 */
@ControllerAdvice
@CommonsLog
public class SXExceptionAdvice {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public XResult<?> handle(Exception e) {
        if (e instanceof SXException) {
            SXException sxException = (SXException) e;
            return XResult.failure(sxException.getCode(), sxException.getInfo());
        } else {
            log.error("【服务器内部错误】{}", e);
            return XResult.failure(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "服务器内部错误");
        }
    }
}
