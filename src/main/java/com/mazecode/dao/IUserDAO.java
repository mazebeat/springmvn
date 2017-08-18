package com.mazecode.dao;

import com.mazecode.model.MUser;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

public interface IUserDAO {
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	List<MUser> all();
	
	MUser activeUser(String userName);

//	void create(MUser user);
//	
//	void update(MUser user);
//	
//	MUser findById(int id);
//	
//	void delete(int id);
}
