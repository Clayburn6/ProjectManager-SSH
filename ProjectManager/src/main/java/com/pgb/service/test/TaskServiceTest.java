package com.pgb.service.test;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pgb.entity.Task;
import com.pgb.service.TaskService;

public class TaskServiceTest {
	private ApplicationContext context = null;
	private TaskService ts;
	@Before
	public void bf() {
		context= new ClassPathXmlApplicationContext("/applicationContext.xml");
		ts = (TaskService) context.getBean("taskService");
	}
	
	@Test
	public void addTaskTest() {
//		for (int i = 1; i < 10; i++) {
//			for (int j = 1; j < 10; j ++) {
//				System.out.println(ts.addTask("任务"+j, "项目"+i));
//			}
//		}
		System.out.println(ts.addTask("任务", "项目项目"));
	}
	
	@Test
	public void searchTaskTest() {
		List<Task> list = ts.searchTask("任务234234", "项目78");
		for (Task task : list) {
			System.out.println(task.getTitle());
		}
	}
	
	@Test
	public void editTaskTest() {
		Task task = new Task();
		task.setTitle("任务1");
		task.setDeadline(new Date(System.currentTimeMillis()));
		task.setDetail("注意细节");
		task.setPriority(10);
		task.setStatus(100);
		System.out.println(ts.editTask(task, "项目"));
	}
	@Test
	public void deleteTaskTest() {
		System.out.println(ts.deletTask("任务1", "项目"));
	}
}
