package com.pgb.service;

import java.util.Map;


public interface ProjectService {

	boolean addProject(String title, String description, String username);

	boolean updateProject(String title, String description, String username);

	boolean deleteProject(String projectTitle);
	
	Map<String, String> showProject(String projectTitle);
}
