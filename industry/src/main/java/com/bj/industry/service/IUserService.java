package com.bj.industry.service;

import com.bj.industry.common.exception.BusinessException;
import com.bj.industry.entity.User;

public interface IUserService {

    /**
     * 根据姓名查找用户
     * @param name
     * @return
     */
    User findUserByName(String name) throws BusinessException;
}
