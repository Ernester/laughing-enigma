package com.bj.industry.repository;

import com.bj.industry.entity.User;
import org.springframework.data.repository.CrudRepository;


/**
 * @author Administrator
 */
public interface UserRepository extends CrudRepository<User,Long> {

    /**
     * 根据姓名查找用户
     * @param name
     * @return
     */
    User findUserByName(String name);
}
