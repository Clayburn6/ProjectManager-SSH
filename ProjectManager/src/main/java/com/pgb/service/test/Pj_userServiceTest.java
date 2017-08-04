package com.pgb.service.test;


import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pgb.service.Pj_userService;

public class Pj_userServiceTest {
	private ApplicationContext context = null;
	private Pj_userService pj_userService;
	@Before
	public void bf() {
		context= new ClassPathXmlApplicationContext("/applicationContext.xml");
		pj_userService = (Pj_userService) context.getBean("pj_userService");
	}
	
	@Test
	public void addUserToProjectTest() {
		for (int i = 1; i < 10; i++) {
			for (int j = 0; j < 26; j++) {
				System.out.println(pj_userService.addUserToProject((char)('a'+j)+"", "项目"+i));
			}
		}
//		System.out.println(projectService.addProject("项目"+100, "这是项目"+100, "aaa")); // 指定一个不存在的人为项目负责人 
	}
	
	
}
