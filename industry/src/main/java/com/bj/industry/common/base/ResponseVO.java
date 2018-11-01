package com.bj.industry.common.base;

public class ResponseVO {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态信息
     */
    private String message;

    /**
     * 消息体
     */
    private Object data;

    public ResponseVO() {
    }

    public ResponseVO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseVO(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static ResponseVO OfMessage(Integer code,String message){
        return new ResponseVO(code,message);
    }

    @Override
    public String toString() {
        return "ResponseVO{" + "code=" + code + ", message='" + message + '\'' + ", data=" + data + '}';
    }
}
