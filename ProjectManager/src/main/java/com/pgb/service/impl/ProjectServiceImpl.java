package com.pgb.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgb.dao.ProjectDao;
import com.pgb.dao.TaskDao;
import com.pgb.dao.UserDao;
import com.pgb.entity.Project;
import com.pgb.entity.Task;
import com.pgb.entity.User;
import com.pgb.service.Pj_userService;
import com.pgb.service.ProjectService;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	ProjectDao projectDao;
	@Autowired
	UserDao userDao;
	@Autowired
	TaskDao taskDao;
	@Autowired
	Pj_userService pj_userService;
	@Override
	public boolean addProject(String title, String description, String username) {
		Project project = new Project();
		project.setTitle(title);
		project.setDescription(description);
		User user = userDao.findUserByName(username);
		if (user == null) {
			return false;
		}
		project.setUser(user);
		return projectDao.addProject(project);		
	}
	@Override
	public boolean updateProject(String title, String description, String username) {
		Project project = projectDao.findProjectByTitle(title);
		User user = userDao.findUserByName(username);
		if (user == null || project == null) {
			return false;
		}
		project.setDescription(description);
		project.setUser(user);
		boolean tag = true;
		try {
			projectDao.updateProject(project);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tag = false;
		}
		return tag;
	}
	@Override
	public boolean deleteProject(String projectTitle) {
		boolean tag1 = taskDao.deleteTaskInProject(projectTitle);
		boolean tag2 = pj_userService.deleteUserFromProject(projectTitle);
		boolean tag3 = projectDao.deleteProject(projectTitle);
		return tag1 && tag2 && tag3;
	}
	@Override
	public Map<String, String> showProject(String projectTitle) {
		String complete = "";
		String total = "";
		List<Task> listComplete = taskDao.findTask(100, projectTitle);
		List<Task> listTotal = taskDao.findTask(projectTitle);
		if (listComplete == null || listComplete.isEmpty()) {
			complete = "0";
		}
		else {
			complete = listComplete.size() + "";
		}
		if (listTotal == null || listTotal.isEmpty()) {
			total = "0";
		}
		else {
			total = listTotal.size() + "";
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("projectTitle", projectTitle);
		map.put("complete", complete);
		map.put("total", total);
		return map;
	}

}
