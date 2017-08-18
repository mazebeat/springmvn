package com.mazecode.service;

import com.mazecode.dao.IUserDAO;
import com.mazecode.model.MUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private IUserDAO userDao;
	
	@Transactional(readOnly = true)
	public List<MUser> all() {
		return userDao.all();
	}

//	public MUser findById(int id) {
//		return userDao.findById(id);
//	}
//	
//	public synchronized void create(MUser user) {
//		userDao.create(user);
//	}
//	
//	public void update(MUser user) {
//		userDao.update(user);
//	}
//	
//	public void delete(int id) {
//		userDao.delete(id);
//	}
}
