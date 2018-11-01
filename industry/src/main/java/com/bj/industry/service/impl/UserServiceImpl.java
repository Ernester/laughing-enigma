package com.bj.industry.service.impl;

import com.bj.industry.common.base.PageVO;
import com.bj.industry.common.exception.BaseExceptionCodes;
import com.bj.industry.common.exception.BusinessException;
import com.bj.industry.entity.Role;
import com.bj.industry.entity.User;
import com.bj.industry.repository.RoleRepository;
import com.bj.industry.repository.UserRepository;
import com.bj.industry.service.IUserService;
import com.bj.industry.vo.UserInfoVO;
import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author fwd
 */
@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UserInfoVO findUserByName(String name) throws BusinessException {
        User user = this.getUser(name);
        UserInfoVO userInfoVO = new UserInfoVO();
        modelMapper.map(user, userInfoVO);
        return userInfoVO;
    }

    @Override
    public void deleteUserInfo(String name) throws BusinessException {
        User user = this.getUser(name);
        //逻辑删除标识
        user.setStatus(User.STATUS_START);
        user.setLastUpdateTime(new Date());
        userRepository.save(user);
    }

    @Override
    public PageVO getUserPage(String userName, Integer page, Integer number) throws BusinessException {
        //校验
        this.getUser(userName);
        Pageable pageable = PageRequest.of(page, number, Sort.by(Sort.Order.asc("createTime")));
        Page<User> pageUsers = userRepository.findAll(pageable);
        List<User> userList = pageUsers.getContent();
        List<UserInfoVO> userVoList;
        if (!userList.isEmpty()){
            userVoList = new ArrayList<>();
            userList.forEach(user -> {
                UserInfoVO userInfoVO = new UserInfoVO();
                modelMapper.map(user, userInfoVO);
                userVoList.add(userInfoVO);
            });
        }
        return PageVO.newBuilder().pageNo(page).pageSize(number).result(userList).build();
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

    /**
     * 获取用户信息
     * @param name 用户名称
     * @return user
     * @throws BusinessException 业务exception
     */
    private User getUser(String name) throws BusinessException {
        User user = userRepository.findUserByName(name);
        if (user == null) {
            throw new BusinessException(BaseExceptionCodes.USER_NOT_EXIST);
        }
        if (User.STATUS_START == user.getStatus()){
            throw new BusinessException(BaseExceptionCodes.USER_NOT_NOMAL);
        }
        return user;
    }
}
