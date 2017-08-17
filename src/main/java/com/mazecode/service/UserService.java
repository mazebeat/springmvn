package com.mazecode.service;

import com.mazecode.dao.IUserDao;
import com.mazecode.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private IUserDao userDao;
	
	public List<User> all() {
		return userDao.all();
	}

//	public User findById(int id) {
//		return userDao.findById(id);
//	}
//	
//	public synchronized void create(User user) {
//		userDao.create(user);
//	}
//	
//	public void update(User user) {
//		userDao.update(user);
//	}
//	
//	public void delete(int id) {
//		userDao.delete(id);
//	}
}
