package com.pgb.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.pgb.dao.UserDao;
import com.pgb.entity.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
	@Autowired
	HibernateTemplate hibernateTemplate;
	
	@Override
	public boolean addUser(User user) {
		boolean tag = true;
		try {
			hibernateTemplate.save(user);
		}
		catch (Exception e) {
			hibernateTemplate.flush();
			e.printStackTrace();
			tag = false;
		}
		
		return tag;
		
	}

	@Override
	public User findUserByName(String username) {
		User user = null;
		try {
			List<User> list = (List<User>) hibernateTemplate.findByNamedParam("from User where username = :username",
					"username",	username);
			if (list != null && !list.isEmpty()) {
				user = list.get(0);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}

	@Override
	public List<User> findAll() {
		return (List<User>) hibernateTemplate.find("from User");
	}

	@Override
	public boolean update(User old) {
		boolean tag = true;
		try {
			hibernateTemplate.update(old);
		}
		catch (Exception e) {
			hibernateTemplate.flush();
			tag = false;
		}
		return tag;
	}

}
