package com.pgb.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;

import com.pgb.dao.UserDao;
import com.pgb.entity.User;
import com.pgb.service.UserService;
import com.pgb.utils.Md5;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Override
	public boolean register(User user) {
		
		try {
        	user.setPassword(Md5.EncoderByMd5(user.getPassword()));
        }
        catch (Exception e) {
        	e.printStackTrace();
        	return false;
        }
		return userDao.addUser(user);
		
	}
	@Override
	public boolean login(String username, String password) {
		User user = userDao.findUserByName(username);
		boolean tag = false;
		try {
			tag =  Md5.checkpassword(password, user.getPassword());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tag;
	}
	@Override
	public List<User> getAllUser() {
		return userDao.findAll();
	}
	@Override
	public User showDetail(String username) {
		return userDao.findUserByName(username);
	}
	@Override
	public boolean update(User user) {
		User old = userDao.findUserByName(user.getUsername());
		if (old == null) {
			return false;
		}
		old.setEmail(user.getEmail());
		old.setPhone(user.getPhone());
		if (user.getHeadphoto() != null && !user.getHeadphoto().equals("")) {
			old.setHeadphoto(user.getHeadphoto());
		}
		return userDao.update(old);
	}
	@Override
	public boolean changepw(String username, String newpasswd) {
		boolean tag = true;
		User old = userDao.findUserByName(username);
		if (old == null) {
			return false;
		}
		try {
        	old.setPassword(Md5.EncoderByMd5(newpasswd));
        	tag = userDao.update(old);
        }
        catch (Exception e) {
        	e.printStackTrace();
        	tag = false;
        }
		return tag;
	}
	@Override
	public boolean changepw(String username, String newpasswd, String oldpasswd) {
		User user = userDao.findUserByName(username);
		boolean tag = false;
		try {
			tag =  Md5.checkpassword(oldpasswd, user.getPassword());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (tag) {
			try {
	        	user.setPassword(Md5.EncoderByMd5(newpasswd));
	        	tag = userDao.update(user);
	        }
	        catch (Exception e) {
	        	e.printStackTrace();
	        	tag = false;
	        }
			return tag;
		}
		else {
			return tag;
		}
	}

}
