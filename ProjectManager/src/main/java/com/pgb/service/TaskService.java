package com.pgb.service;

import java.util.List;

import com.pgb.entity.Task;

public interface TaskService {

	
	public boolean deletTask(String title, String projectTitle);

	public boolean addTask(String taskTitle, String projectTitle);

	public List<Task> searchTask(String title, String projectTitle);

	public boolean editTask(Task task, String projectTitle);
}
