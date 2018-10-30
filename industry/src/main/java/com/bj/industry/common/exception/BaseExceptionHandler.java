package com.bj.industry.common.exception;

import com.bj.industry.common.base.ResponseVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 * 统一异常处理
 * @author fwd
 */
@RestController
@ControllerAdvice
public class BaseExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseExceptionHandler.class);

    @ExceptionHandler(RuntimeException.class)
    public ResponseVO runtimeExcept(RuntimeException exception, HttpServletRequest request) {
        LOGGER.error("RuntimeException: {} ", this.getRequestInfo(request), exception);
        return ResponseVO.OfMessage(BaseExceptionCodes.FAILED);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseVO businessExcept(BusinessException exception, HttpServletRequest request) {
        LOGGER.error("BusinessException, code:{}, msg:{}, {}", exception.getCode(), exception.getMessage(), this.getRequestInfo(request));
        return  ResponseVO.OfMessage(exception.getCode());
    }


    @ExceptionHandler(Exception.class)
    public ResponseVO except(Exception exception, HttpServletRequest request) {
        LOGGER.error("Exception: {} ", this.getRequestInfo(request), exception);
        return ResponseVO.OfMessage(BaseExceptionCodes.FAILED);
    }


    /**
     * 请求信息
     * @param request request
     * @return str
     */
    private String getRequestInfo(HttpServletRequest request) {
        return String.format("url：%s , method：%s", request.getRequestURL(),  request.getMethod());
    }

}
