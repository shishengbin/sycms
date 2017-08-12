package com.sy.dao;

import com.sy.entity.User;

import java.util.List;

/**
 * <p>User: Shi Sheng bin
 * <p>Date: 16-12-30
 * <p>Version: 1.0
 */
public interface UserDao {

	public User createUser(User user);

	public User updateUser(User user);

	public void deleteUser(Long userId);

	User findOne(Long userId);

	List<User> findAll();

	User findByUsername(String username);

}
