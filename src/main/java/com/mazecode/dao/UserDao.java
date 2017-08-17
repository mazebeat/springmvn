package com.mazecode.dao;

import com.mazecode.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
@Repository
public class UserDao implements IUserDao {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@SuppressWarnings("unchecked")
	public List<User> all() {
		String hql = "FROM User AS u ORDER BY u.id";
		return ((List<User>) hibernateTemplate.find(hql));
	}

//	public User findById(int id) {
//		return hibernateTemplate.get(User.class, id);
//	}
//
//	public void create(User user) {
//		user.setCreatedAt(new Date());
//		hibernateTemplate.save(user);
//	}
//
//	public void update(User user) {
//		User u = findById(user.getId());
//		u.setName(user.getName());
//		u.setEmail(user.getEmail());
//		u.setPassword(user.getPassword());
//		u.setUpdatedAt(new Date());
//		hibernateTemplate.update(u);
//	}
//
//	public void delete(int pid) {
//		hibernateTemplate.delete(findById(pid));
//	}
}
