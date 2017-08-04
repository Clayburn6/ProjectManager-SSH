package com.pgb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;

import com.pgb.dao.Pj_userDao;
import com.pgb.dao.ProjectDao;
import com.pgb.dao.UserDao;
import com.pgb.entity.Pj_user;
import com.pgb.entity.Project;
import com.pgb.entity.User;
import com.pgb.service.Pj_userService;

@Service("pj_userService")
public class Pj_userServiceImpl implements Pj_userService {
	@Autowired
	Pj_userDao pj_userDao;
	
	@Override
	public boolean addUserToProject(String username, String projectTitle) {
		return pj_userDao.addUserToProject(username, projectTitle);
	}

	@Override
	public boolean deleteUserFromProject(String projectTitle) {
		return pj_userDao.deleteUserFromProject(projectTitle);
	}

	public  List<Pj_user> findPUByTitle(String projectTitle) {
		return pj_userDao.findPUByTitle(projectTitle);
	}

}
