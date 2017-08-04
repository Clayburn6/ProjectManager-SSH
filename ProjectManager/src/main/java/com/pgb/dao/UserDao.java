package com.pgb.dao;

import java.util.List;

import com.pgb.entity.User;

public interface UserDao {
	// 天假用户
	boolean addUser(User user);
	// 根据用户名查找,由于用户名是unique，所以只会找出来一个
	User findUserByName(String username);
	// 找出所有用户
	List<User> findAll();
	boolean update(User old);
}
