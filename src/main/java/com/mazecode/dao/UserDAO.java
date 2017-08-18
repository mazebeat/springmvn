package com.mazecode.dao;

import com.mazecode.model.MUser;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserDAO implements IUserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<MUser> all() {
		String            hql   = "FROM MUser AS u ORDER BY u.id";
		TypedQuery<MUser> query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.getResultList();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public MUser activeUser(String email) {
		MUser user = new MUser();
		try {
			String            hql   = "FROM MUser AS u WHERE u.email = :email";
			TypedQuery<MUser> query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("email", email);
			List<?>           list  = query.getResultList();
			if (!list.isEmpty()) {
				user = (MUser) list.get(0);
			}
//		short             enabled         = 1;
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
		return user;
	}

//	public MUser findById(int id) {
//		return hibernateTemplate.get(MUser.class, id);
//	}
//
//	public void create(MUser user) {
//		user.setCreatedAt(new Date());
//		hibernateTemplate.save(user);
//	}
//
//	public void update(MUser user) {
//		MUser u = findById(user.getId());
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
