package com.pgb.service.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pgb.entity.User;
import com.pgb.service.UserService;

public class UserServiceTest {
	private ApplicationContext context = null;
	private UserService userService;
	@Before
	public void bf() {
		context= new ClassPathXmlApplicationContext("/applicationContext.xml");
		userService = (UserService) context.getBean("userService");
	}
	
	@Test
	public void registerTest() {
		for (int i = 0; i < 26; i++) {
			User user = new User();
			user.setUsername((char)('a'+i)+"");
			user.setPassword("123456");
			user.setEmail("123@qq.com");
			user.setPhone("123456");
			user.setHeadphoto("www.baidu.com");
			userService.register(user);
		}
		
	}
	
	@Test
	public void loginTest() {
		if (userService.login("a", "123456")) {
			System.out.println("登录成功");
		}
		else {
			System.out.println("登录失败");
		}
		
		if (userService.login("a", "4444")) {
			System.out.println("登录成功");
		}
		else {
			System.out.println("登录失败");
		}
	}
	
	@Test
	public void showAllTest() {
		List<User> list = userService.getAllUser();
		for (User user : list) {
			System.out.println(user.toString());
		}
	}
	
	@Test
	public void showDetailTest() {
		System.out.println(userService.showDetail("a").toString());
		System.out.println(userService.showDetail("asdf").toString()); // 不存在的用户
	}
	
	@Test
	public void updateTest() {
		User user = new User();
//		user.setUsername("a");
		user.setUsername("asdf"); // 不存在的用户
		user.setEmail("qwer");
		user.setPhone("gggggg");
		user.setHeadphoto("www.souhu.com");
		System.out.println(userService.update(user));
	}
	
	@Test
	public void changepsByUser() {
//		System.out.println(userService.changepw("a", "565656"));
//		System.out.println(userService.changepw("aasd", "565656")); // 不存在的用户
//		System.out.println(userService.changepw("b", "234567", "123456"));
//		System.out.println(userService.login("b", "234567"));
		System.out.println(userService.changepw("asdf", "3333"));
	}
}
