package com.mazecode.service;

import com.mazecode.dao.IUserDAO;
import com.mazecode.model.MUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AppUserDetailsService implements UserDetailsService {
	@Autowired
	private IUserDAO userDAO;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// email = "diego.feliud@gmail.com";
		MUser activeUserInfo = userDAO.activeUser(email);
		// TODO: Change role by user role
		GrantedAuthority authority   = new SimpleGrantedAuthority("ROLE_ADMIN");
		UserDetails      userDetails = new User(activeUserInfo.getEmail(), activeUserInfo.getPassword(), Arrays.asList(authority));
		return userDetails;
	}
}
