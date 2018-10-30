package com.bj.industry.service;

import com.bj.industry.common.exception.BusinessException;
import com.bj.industry.entity.User;
import com.bj.industry.vo.UserInfoVO;

public interface IUserService {

    /**
     * 根据姓名查找用户
     * @param name
     * @return
     */
    UserInfoVO findUserByName(String name) throws BusinessException;

}
