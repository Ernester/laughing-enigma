package com.bj.industry.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
public class LoginUrlEntryPoint extends LoginUrlAuthenticationEntryPoint {

    private PathMatcher pathMatcher = new AntPathMatcher();

    private final Map<String, String> authEntryPoint;

    /**
     *角色登录入口映射
     */
    public LoginUrlEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
        authEntryPoint = new HashMap<>();
        authEntryPoint.put("/user/**", "/user/login");
    }

    /**
     * 根据请求跳转到指定页面，基于loginForUrl
     * @param request
     * @param response
     * @param exception
     * @return
     */
    @Override
    protected String determineUrlToUseForThisRequest(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        String url = request.getRequestURI().replace(request.getContextPath(),"");
        for (Map.Entry<String, String> entity : authEntryPoint.entrySet()) {
            if(pathMatcher.match(entity.getKey(),url)){
                return entity.getValue();
            }
        }
        return super.determineUrlToUseForThisRequest(request, response, exception);
    }
}
