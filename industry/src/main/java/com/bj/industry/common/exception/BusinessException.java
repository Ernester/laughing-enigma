package com.bj.industry.common.exception;

import static com.bj.industry.common.message.MessageContext.message;

public class BusinessException extends Exception{

    private static final long serialVersionUID = -7967655278961335496L;

    private Integer code;

    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException( Integer code) {
        this(message(code));
        this.code = code;
    }

    public BusinessException(Integer code, Throwable cause) {
        this(message(code), cause);
        this.code = code;
    }

    public BusinessException(String message, Throwable cause) {
        super(message);
    }

    public BusinessException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public BusinessException(int code, String msg, Throwable cause) {
        this(msg, cause);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
