package com.pgb.dao;

import com.pgb.entity.Project;

public interface ProjectDao {
	boolean addProject(Project project);
	Project findProjectByTitle(String title);
	boolean updateProject(Project project);
	boolean deleteProject(String projectTitle);
}
