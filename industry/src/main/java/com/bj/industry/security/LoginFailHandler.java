package com.bj.industry.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败处理
 * @author fwd
 */
public class LoginFailHandler extends SimpleUrlAuthenticationFailureHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginFailHandler.class);

    private LoginUrlEntryPoint loginUrlEntryPoint;

    public LoginFailHandler(LoginUrlEntryPoint loginUrlEntryPoint) {
        this.loginUrlEntryPoint = loginUrlEntryPoint;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        //获取跳转的URL
        String targetUrl = loginUrlEntryPoint.determineUrlToUseForThisRequest(request, response, exception);
        String defaultFailureUrl = targetUrl + "?" + exception.getMessage();
        LOGGER.debug("登录失败调试:{}",defaultFailureUrl);
        super.setDefaultFailureUrl(defaultFailureUrl);
        super.onAuthenticationFailure(request,response,exception);
    }
}
