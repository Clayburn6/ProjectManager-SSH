package com.pgb.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.pgb.dao.ProjectDao;
import com.pgb.entity.Project;

@Repository("projectDao")
public class ProjectDaoImpl implements ProjectDao {
	@Autowired
	HibernateTemplate hibernateTemplate;
	@Override
	public boolean addProject(Project project) {
		boolean tag = true;
		try {
			hibernateTemplate.save(project);
		}
		catch (Exception e) {
			hibernateTemplate.flush();
			e.printStackTrace();
			tag = false;
		}
		return tag;
	}
	@Override
	public Project findProjectByTitle(String title) {
		Project project = null;
		try {
			List<Project> list = (List<Project>) hibernateTemplate.findByNamedParam("from Project where title = :title",
					"title", title);
			if (!list.isEmpty() && list != null) {
				project = list.get(0);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return project;
		
	}
	@Override
	public boolean updateProject(Project project) {
		boolean tag = true;
		try {
			hibernateTemplate.update(project);
		}
		catch (Exception e) {
			hibernateTemplate.flush();
			e.printStackTrace();
			tag = false;
		}
		return tag;
	}
	@Override
	public boolean deleteProject(String projectTitle) {
		boolean tag = true;
		try {
			Project project = findProjectByTitle(projectTitle);
			hibernateTemplate.delete(project);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			hibernateTemplate.flush();
			tag = false;
		}
		
		return tag;
	}

}
