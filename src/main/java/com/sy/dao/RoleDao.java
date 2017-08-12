package com.sy.dao;

import com.sy.entity.Role;

import java.util.List;

/**
 * <p>User: Shi Shengbin
 * <p>Date: 16-12-30
 * <p>Version: 1.0
 */
public interface RoleDao {

    public Role createRole(Role role);
    public Role updateRole(Role role);
    public void deleteRole(Long roleId);

    public Role findOne(Long roleId);
    public List<Role> findAll();
}
