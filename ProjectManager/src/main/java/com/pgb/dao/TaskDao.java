package com.pgb.dao;

import java.util.List;

import com.pgb.entity.Task;

public interface TaskDao {
	boolean addTask(Task task);
	boolean deleteTask(String title, String projectTitle);
	boolean deleteTaskInProject(String projectTitle);
	boolean updateTask(Task task, String projectTitle);
	Task findTaskByTitle(String taskTitle, String projectTitle);
	// 模糊查找
	List<Task> findTask(String taskTitle, String projectTitle);
	// 分页查询
	List<Task> findTaskForPage(String hql, int offset, int length);
	
	List<Task> findTask(int status, String projectTitle);
	
	List<Task> findTask(String projectTitle);
}
