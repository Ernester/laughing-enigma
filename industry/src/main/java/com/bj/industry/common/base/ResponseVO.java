package com.bj.industry.common.base;

import com.alibaba.fastjson.JSON;
import com.bj.industry.common.exception.BaseExceptionCodes;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import static com.bj.industry.common.message.MessageContext.message;

public class ResponseVO<T> {

    /**
     * 状态码
     */
    @ApiModelProperty("状态码，200表示正常")
    private Integer code;

    /**
     * 状态信息
     */
    @ApiModelProperty("错误信息")
    private String message;

    /**
     * 消息体
     */
    @ApiModelProperty("消息体")
    private Object data;

    public ResponseVO() {
        this.code = BaseExceptionCodes.SUCCESS;
        this.message = message(this.code);
    }

    public ResponseVO(Integer code, String message) {
        this(code, message(code), message);
    }

    public ResponseVO(Integer code) {
        this(code, message(code), null);
    }

    public ResponseVO(int code, List<T> data) {
        this(code, message(code), data);
    }

    public ResponseVO(int code, Object data) {
        this(code, message(code), data);
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

    public static ResponseVO OfMessage(Integer code){
        return new ResponseVO(code);
    }

    public static ResponseVO OfMessage(Integer code,String message){
        return new ResponseVO(code,message);
    }

    public static ResponseVO OfMessage(Integer code,Object message){
        return new ResponseVO(code,message);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
