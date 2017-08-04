package com.pgb.dao.test;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pgb.dao.ProjectDao;
import com.pgb.dao.TaskDao;
import com.pgb.dao.UserDao;
import com.pgb.entity.Project;
import com.pgb.entity.Task;
import com.pgb.entity.User;

public class ProjectAndTaskTest {
	ApplicationContext context;
	ProjectDao projectDao;
	TaskDao taskDao;
	UserDao userDao;
	@Before
	public void bf() {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		projectDao = (ProjectDao) context.getBean("projectDao");
		userDao = (UserDao) context.getBean("userDao");
	}
	
	@Test
	public void addProjectTest() {
		Project project = new Project();
		project.setTitle("项目1");
		project.setDescription("这是项目1");
		User user = userDao.findUserByName("pgb");
		project.setUser(user);
		projectDao.addProject(project);
	}
	@Test 
	public void eidtTask() {
//		Task task = new Task();
//		task.setTitle("任务1");
//		task.set
	}
	
	@Test
	public void addTask() {
		Project project = projectDao.findProjectByTitle("项目1");
//		Project project = new Project();
//		project.setTitle("项目1");
//		project.setDescription("这是项目1");
		Task task = new Task();
		task.setTitle("任务1");
		Set<Task> set = new HashSet<Task>();
		set.add(task);
		project.setTask(set);
		projectDao.updateProject(project);
	}
}
