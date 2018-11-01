package com.bj.industry.repository;

import com.bj.industry.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * @author Administrator
 */
public interface UserRepository extends PagingAndSortingRepository<User,Long>, JpaSpecificationExecutor<User> {

    /**
     * 根据姓名查找用户
     * @param name
     * @return
     */
    User findUserByName(String name);
}
