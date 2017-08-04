package com.pgb.dao;

import java.util.List;

import com.pgb.entity.Pj_user;

public interface Pj_userDao {
	boolean addUserToProject(String username, String projectTitle);
	boolean deleteUserFromProject(String projectTitle);
	List<Pj_user> findPUByTitle(String projectTitle);
}
