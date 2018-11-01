package com.bj.industry.service;

import com.bj.industry.common.base.PageVO;
import com.bj.industry.common.exception.BusinessException;
import com.bj.industry.vo.UserInfoVO;

public interface IUserService {

    /**
     * 根据姓名查找用户
     * @param name 用户名称
     * @return info
     */
    UserInfoVO findUserByName(String name) throws BusinessException;

    /**
     * 根据用户名删除用户
     * @param name 用户名
     */
    void deleteUserInfo(String name) throws BusinessException;

    /**
     * 分页获取用户信息
     * @param userName 用户名称
     * @param page 页码
     * @param number 分页条数
     * @return User
     */
    PageVO getUserPage(String userName, Integer page, Integer number) throws BusinessException;
}
