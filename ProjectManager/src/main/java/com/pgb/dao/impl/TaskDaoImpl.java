package com.pgb.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.pgb.dao.ProjectDao;
import com.pgb.dao.TaskDao;
import com.pgb.entity.Project;
import com.pgb.entity.Task;

@Component("taskDao")
public class TaskDaoImpl implements TaskDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;
	@Autowired
	private ProjectDao projectDao;
	@Override
	public boolean addTask(Task task) {
		// TODO Auto-generated method stub
		hibernateTemplate.save(task);
		
		return true;
	}

	@Override
	public boolean deleteTask(String taskTitle, String projectTitle) {
		boolean tag = true;
		try {
			Task task = findTaskByTitle(taskTitle, projectTitle);
			if (task == null) {
				return false;
			}
			hibernateTemplate.delete(task);
		}
		catch (Exception e) {
			hibernateTemplate.flush();
			e.printStackTrace();
			tag = false;
		}
		return tag;
	}

	@Override
	public boolean updateTask(Task task, String projectTitle) {
		// TODO Auto-generated method stub
		boolean tag = true;
		Task temp = findTaskByTitle(task.getTitle(), projectTitle);
		if (temp == null) {
			return false;
		}
		
		temp.setDeadline(task.getDeadline());
		temp.setDetail(task.getDetail());
		temp.setPriority(task.getPriority());
		temp.setStatus(task.getStatus());
		
		try {
			hibernateTemplate.update(temp);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tag = false;
		}
		
		return tag;
	}

	// 模糊查询
	@Override
	public List<Task> findTask(String taskTitle, String projectTitle) {
		// TODO Auto-generated method stub
		Project project = projectDao.findProjectByTitle(projectTitle);
		if (project == null) {
			return null;
		}
		List<Task> list = (List<Task>) hibernateTemplate.findByNamedParam("from Task where title like :title and project = :project",
				new String[] {"title", "project"}, new Object[] {"%"+taskTitle+"%", project});
		return list;
	}

	@Override
	public List<Task> findTaskForPage(final String hql, final int offset, final int length) {
		@SuppressWarnings("unchecked")
		List<Task> list = (List<Task>) hibernateTemplate.execute(new HibernateCallback<List<Task>>() {

			@Override
			public List<Task> doInHibernate(Session session) throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				Query query = session.createQuery(hql);
				query.setFirstResult(offset);
				query.setMaxResults(length);
				List<Task> list = query.list();
				return list;
			}     
		});
   
		return list;
	}

	@Override
	public Task findTaskByTitle(String taskTitle, String projectTitle) {
		Project project = projectDao.findProjectByTitle(projectTitle);
		if (project == null) {
			return null;
		}
		try {
			List<Task> list = (List<Task>) hibernateTemplate.findByNamedParam("from Task where title = :title and project = :project", 
					new String[] {"title", "project"}, new Object[] {taskTitle, project});
			if (list != null && !list.isEmpty()) {
				return list.get(0);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteTaskInProject(String projectTitle) {
		Project project = projectDao.findProjectByTitle(projectTitle);
		if (project == null) {
			return false;
		}
		List<Task> list = (List<Task>) hibernateTemplate.find("from Task where project = ?", project);
		hibernateTemplate.deleteAll(list);
		return true;
	}

	@Override
	public List<Task> findTask(int status, String projectTitle) {
		Project project = projectDao.findProjectByTitle(projectTitle);
		if (project == null) {
			return null;
		}
		try {
			return (List<Task>) hibernateTemplate.findByNamedParam("from Task where status = :status and project = :project", 
					new String[] {"status", "project"}, new Object[] {status, project});
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Task> findTask(String projectTitle) {
		Project project = projectDao.findProjectByTitle(projectTitle);
		if (project == null) {
			return null;
		}
		try {
			return (List<Task>) hibernateTemplate.findByNamedParam("from Task where project = :project", 
					"project", project);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
