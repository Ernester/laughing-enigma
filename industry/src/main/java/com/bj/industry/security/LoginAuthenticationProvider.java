package com.bj.industry.security;

import com.bj.industry.entity.User;
import com.bj.industry.repository.RoleRepository;
import com.bj.industry.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class LoginAuthenticationProvider implements AuthenticationProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginAuthenticationProvider.class);

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) {
        String userName = authentication.getName();
        String password = (String) authentication.getCredentials();
        LOGGER.debug("用户名称:{},密码:{}", userName, password);
        User user = userRepository.findUserByName(userName);
        if(user == null){
            throw new AuthenticationCredentialsNotFoundException("authError");
        }
        passwordEncoder.encode(user.getPassword());
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
