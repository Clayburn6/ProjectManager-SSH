package com.pgb.dao.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pgb.dao.Pj_userDao;
import com.pgb.dao.ProjectDao;
import com.pgb.dao.UserDao;
import com.pgb.entity.Pj_user;
import com.pgb.entity.Project;
import com.pgb.entity.User;

public class Pj_userDaoTest {
	ApplicationContext context;
	Pj_userDao pud;
	ProjectDao projectDao;
	UserDao userDao;
	@Before
	public void bf() {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		pud = (Pj_userDao) context.getBean("pj_userDao");
		projectDao = (ProjectDao) context.getBean("projectDao");
		userDao = (UserDao) context.getBean("userDao");
	}
	
	@Test
	public void addtest() {
		Project project = projectDao.findProjectByTitle("项目1");
		User user = userDao.findUserByName("pgb");
		Pj_user pu = new Pj_user();
		pu.setProject(project);
		pu.setUser(user);
		
//		pud.addpj_user(pu);
	}
}
