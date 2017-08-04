package com.pgb.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgb.dao.ProjectDao;
import com.pgb.dao.TaskDao;
import com.pgb.entity.Project;
import com.pgb.entity.Task;
import com.pgb.service.TaskService;

@Service("taskService")
public class TaskServiceImpl implements TaskService {
	private int status = 50; // 状态 50活动中任务， 100为已完成任务
	private String style = "createtime"; // createtime desc创建日期倒序，deadline desc 倒序  priority desc任务紧急情况
	@Autowired
	private TaskDao taskDao;
	@Autowired
	private ProjectDao projectDao;
	
	
	
	@Override
	public boolean addTask(String taskTitle, String projectTitle) {
		// TODO Auto-generated method stub
		Task task = new Task();
		task.setTitle(taskTitle);
		Project project = projectDao.findProjectByTitle(projectTitle);
		if (project == null) {
			return false;
		}
		project.getTask().add(task);
		return projectDao.updateProject(project);
	}
	@Override
	public boolean deletTask(String taskTitle, String projectTitle) {
		// TODO Auto-generated method stub
		return taskDao.deleteTask(taskTitle, projectTitle);
	}
	@Override
	public List<Task> searchTask(String taskTitle, String projectTitle) {
		return (List<Task>) taskDao.findTask(taskTitle, projectTitle);

	}
	@Override
	public boolean editTask(Task task, String projectTitle) {
		return taskDao.updateTask(task, projectTitle);

	}

	
}
