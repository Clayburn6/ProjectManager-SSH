package com.pgb.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.pgb.dao.Pj_userDao;
import com.pgb.dao.ProjectDao;
import com.pgb.dao.UserDao;
import com.pgb.entity.Pj_user;
import com.pgb.entity.Project;
import com.pgb.entity.User;

@Repository("pj_userDao")
public class Pj_userDaoImpl implements Pj_userDao {
	@Autowired
	HibernateTemplate hibernateTemplate;
	@Autowired
	UserDao userDao;
	@Autowired
	ProjectDao projectDao;
	@Override
	public boolean addUserToProject(String username, String projectTitle) {
		User user = userDao.findUserByName(username);
		if (user == null) {
			return false;
		}
		Project project = projectDao.findProjectByTitle(projectTitle);
		if (project == null) {
			return false;
		}
		Pj_user pu = new Pj_user();
		pu.setProject(project);
		pu.setUser(user);
		boolean tag = true;
		try {
			hibernateTemplate.save(pu);
		}
		catch (Exception e) {
			// TODO: handle exception
			hibernateTemplate.flush();
			e.printStackTrace();
			tag = false;
		}
		return tag;
	}
	@Override
	public boolean deleteUserFromProject(String projectTitle) {
		boolean tag = true;
		try {
			List<Pj_user> list = findPUByTitle(projectTitle);
			for (Pj_user pu : list) {
				pu.setProject(null);
				pu.setUser(null);
				hibernateTemplate.update(pu);
			}
			hibernateTemplate.deleteAll(list);
		}
		catch (Exception e) {
			hibernateTemplate.flush();
			e.printStackTrace();
			tag = false;
			// TODO: handle exception
			System.out.println("删除不了");
		}
		
		return tag;
	}

	public  List<Pj_user> findPUByTitle(String projectTitle) {
		Project project = projectDao.findProjectByTitle(projectTitle);
		return (List<Pj_user>) hibernateTemplate.findByNamedParam("from Pj_user where project = :project", "project", project);
	}

}
