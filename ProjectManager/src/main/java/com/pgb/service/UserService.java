package com.pgb.service;

import java.util.List;

import com.pgb.entity.User;

public interface UserService {
	boolean register(User user);
	boolean login(String username, String password);
	List<User> getAllUser();
	User showDetail(String username);
	boolean update(User user);
	boolean changepw(String username, String newpasswd);
	boolean changepw(String username, String newpasswd, String oldpasswd);
}
