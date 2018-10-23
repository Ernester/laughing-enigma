package com.bj.industry.common.enums;

/**
 * 状态码
 * @author fwd
 */

public enum Status {
    /**
     *
     */
    SUCCESS(200, "OK"),
    BAD_REQUEST(400, "Bad Request"),
    FORBIDDEN(403,"forbidden"),
    NOT_FOUND(404, "Not Found"),
    INTERNAL_SERVER_ERROR(500, "Unknown Internal Error"),
    NOT_VALID_PARAM(40000, "Not valid Params"),
    NOT_SUPPORTED_OPERATION(40001, "Operation not supported"),
    NOT_LOGIN(40002, "Not Login");

    private Integer code;

    private String message;

    Status(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
