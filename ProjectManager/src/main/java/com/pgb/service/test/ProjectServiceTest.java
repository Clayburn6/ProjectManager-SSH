package com.pgb.service.test;

import java.util.Map;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pgb.service.ProjectService;
import com.pgb.service.UserService;

public class ProjectServiceTest {
	private ApplicationContext context = null;
	private ProjectService projectService;
	private UserService userService;
	@Before
	public void bf() {
		context= new ClassPathXmlApplicationContext("/applicationContext.xml");
		projectService = (ProjectService) context.getBean("projectService");
	}
	
	@Test
	public void addProjectTest() {
		for (int i = 1; i < 10; i++) {
			Random rand = new Random();
			rand.setSeed(System.currentTimeMillis());
			System.out.println(projectService.addProject("项目"+i, "这是项目"+i, (char)('a'+rand.nextInt(26)) + ""));
		}
//		System.out.println(projectService.addProject("项目"+100, "这是项目"+100, "aaa")); // 指定一个不存在的人为项目负责人 
	}
	
	@Test
	public void showProjectTest() {
		Map<String, String> map = projectService.showProject("项目");
		System.out.println(map.get("projectTitle"));
		System.out.println(map.get("complete"));
		System.out.println(map.get("total"));
	}
	
	@Test
	public void editProjectTest() {
		System.out.println(projectService.updateProject("项目1", "这不是项目", "er"));
	}
	
	@Test
	public void deleteProjectTest() {
		System.out.println(projectService.deleteProject("项目2"));
	}
}