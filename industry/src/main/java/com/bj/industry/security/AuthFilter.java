package com.bj.industry.security;

import com.bj.industry.entity.User;
import com.bj.industry.repository.UserRepository;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private UserRepository userRepository;


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        String name = obtainUsername(request);
        if (!Strings.isNullOrEmpty(name)) {
            request.setAttribute("username", name);
            return super.attemptAuthentication(request, response);
        }

        User user = userRepository.findUserByName(name);
        if (user == null) {
            return null;
        }

        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }
}
