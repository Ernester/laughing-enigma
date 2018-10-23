package com.bj.industry.controller;

import com.bj.industry.common.base.ResponseVO;
import com.bj.industry.common.constant.BusunessConstant;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@Controller
public class WebErrorController implements ErrorController {

    /** error路径 */
    private static final String ERROR_PATH = "/error";

    /**
     * error属性
     */
    private ErrorAttributes errorAttributes;

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @RequestMapping(value = ERROR_PATH, produces = "text/html")
    public String errorHandler(HttpServletResponse response){
        int status = response.getStatus();
        if (BusunessConstant.NOT_FOUND == status || BusunessConstant.BAD_REQUEST ==status || BusunessConstant.FORBIDDEN == status){
            return String.valueOf(status);
        }
        return "index";
    }

    /**
     * 除Web页面外的错误处理，比如Json/XML等
     */
    @RequestMapping(value = ERROR_PATH)
    public @ResponseBody ResponseVO errorApiHandler(HttpServletRequest request){
        WebRequest webRequest = new ServletWebRequest(request);
        Map<String,Object> attr = this.errorAttributes.getErrorAttributes(webRequest,false);
        if (attr == null) {
            return ResponseVO.OfMessage(BusunessConstant.INTERNAL_SERVER_ERROR,request.getRemoteUser()+"|error");
        }
        Integer status = (Integer)request.getAttribute("javax.servlet.error.status_code");
        if(status==null){
            status = 500;
        }
        return ResponseVO.OfMessage(status, String.valueOf(attr.getOrDefault("message", "error")));
    }

}
