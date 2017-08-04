package com.pgb.dao.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pgb.dao.UserDao;
import com.pgb.entity.User;

public class UserDaoTest {
	ApplicationContext context;
	UserDao userDao;
	@Before
	public void bf() {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		userDao = (UserDao) context.getBean("userDao");
	}
	@Test
	public void userAddTest() {
		User user = new User();
		user.setUsername("pgb");
		user.setPassword("123");
		user.setEmail("123@qq.com");
		user.setPhone("123456");
		user.setHeadphoto("www.baidu.com");
		user.setIssupper(1);
		userDao.addUser(user);
	}
	@Test
	public void findUserByNameTest() {
		User user = userDao.findUserByName("pgb");
		System.out.println(user.getId());
	}
}
