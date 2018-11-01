package com.bj.industry.security;

import com.bj.industry.common.exception.BusinessException;
import com.bj.industry.entity.User;
import com.bj.industry.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class LoginAuthenticationProvider implements AuthenticationProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginAuthenticationProvider.class);

    @Autowired
    private IUserService userService;

    private PasswordEncoder passwordEncoder  = new BCryptPasswordEncoder();

    @Override
    public Authentication authenticate(Authentication authentication) {
        String userName = authentication.getName();
        String password = (String) authentication.getCredentials();
        LOGGER.debug("用户名称:{},密码:{}", userName, password);
        User user = null;
        try {
            user = userService.findUserByName(userName);
        } catch (BusinessException e) {
            LOGGER.error("获取用户异常:{}", e);
        }
        if(user == null){
            throw new AuthenticationCredentialsNotFoundException("authError");
        }
//passwordEncoder.matches(password,passwordEncoder.encode(user.getPassword()))
        if(true){
            return new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
        }else{
            throw new BadCredentialsException("authError");
        }
    }


    /**
     * 支持所有的认证类
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
