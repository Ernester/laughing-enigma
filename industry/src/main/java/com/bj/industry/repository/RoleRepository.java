package com.bj.industry.repository;

import com.bj.industry.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 角色
 */
public interface RoleRepository extends CrudRepository<Role,Long> {

    /**
     * 获取角色
     * @param userId
     * @return
     */
    List<Role> findRoleByUserId(Long userId);

}
