package com.bj.industry.common.exception;

public interface BaseExceptionCodes {

    /**
     * 服务器繁忙,请稍后再试!
     */
    int HTTP_ERR = 99;

    /**
     * 参数异常
     */
    int PARAM_ERROR = 100;

    /**
     * 用户未登录!
     */
    int USER_NOT_LOGIN = 101;

    /**
     * 用户不存在
     */
    int USER_NOT_EXIST = 102;

    /**
     * 用户账号异常，已经被禁用
     */
    int USER_NOT_NOMAL = 103;

    /**
     * 成功
     */
    int SUCCESS = 200;

    /**
     * 系统异常
     */
    int FAILED = -1;
}
