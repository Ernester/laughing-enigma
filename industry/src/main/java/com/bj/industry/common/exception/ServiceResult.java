package com.bj.industry.common.exception;

import com.bj.industry.common.base.ResponseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

import static com.bj.industry.common.message.MessageContext.message;

/**
 * 返回VO封装
 */
public class ServiceResult {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceResult.class);

    public static String returnValue(int code) {
        String response =  new ResponseVO<>(code).toString();
        if (code != BaseExceptionCodes.SUCCESS) {
            LOGGER.error(response);
        }
        return response;
    }

    public static <T> ResponseVO<T> returnResponseVO(int code, T data) {
        if (data instanceof List) {
            return new ResponseVO<>(code, (List<T>) data);
        } else {
            return new ResponseVO<>(code, Arrays.asList(data));
        }
    }

    public static <T> ResponseVO<T> returnResponseVO(T data) {
        int code = BaseExceptionCodes.SUCCESS;
        if (data instanceof List) {
            return new ResponseVO<>(code, (List<T>) data);
        } else {

            return new ResponseVO<>(code, Arrays.asList(data));
        }
    }

    public static <T> String returnValue(int code, List<T> data) {
        ResponseVO<T> vo = new ResponseVO<>(code, data);
        String json = vo.toString();
        if (code != BaseExceptionCodes.SUCCESS) {
            LOGGER.error(json);
        }
        return json;
    }

    public static <T> String returnValue(int code, String msg, T data) {
        if (msg == null) {
            msg = message(code);
        }
        ResponseVO<T> vo = new ResponseVO<>(code, msg, data == null ? null : Arrays.asList(data));
        String json = vo.toString();
        if (code != BaseExceptionCodes.SUCCESS) {
            LOGGER.error(json);
        }
        return json;
    }
}
