package com.bj.industry.service.impl;

import com.bj.industry.common.exception.BusinessException;
import com.bj.industry.entity.Role;
import com.bj.industry.entity.User;
import com.bj.industry.repository.RoleRepository;
import com.bj.industry.repository.UserRepository;
import com.bj.industry.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fwd
 */
@Service
public class UserServiceImpl implements IUserService,UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public User findUserByName(String name) throws BusinessException {
        LOGGER.info("user find name:{}", name);
        if (StringUtils.isBlank(name)){
            throw new BusinessException("参数异常");
        }
        User user = userRepository.findUserByName(name);
        if (user == null) {
            return null;
        }
        List<Role> roles = roleRepository.findRoleByUserId(user.getId());

        if(roles == null || roles.isEmpty()){
            throw new DisabledException("无权限");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName())));
        user.setAuthorityList(authorities);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("user find name:{}", username);
        if (StringUtils.isBlank(username)){
            throw new UsernameNotFoundException("用户名不存在");
        }
        User user = userRepository.findUserByName(username);
        if (user == null) {
            LOGGER.error("用户名不存在:{}",username);
            throw new UsernameNotFoundException("用户名不存在");
        }
        List<Role> roles = roleRepository.findRoleByUserId(user.getId());
        if(roles == null || roles.isEmpty()){
            LOGGER.error("用户权限不足:{}",username);
            throw new DisabledException("无权限");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName())));
        user.setAuthorityList(authorities);
        return user;
    }
}
