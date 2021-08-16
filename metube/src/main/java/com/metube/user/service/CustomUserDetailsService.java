package com.metube.user.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.metube.user.dao.userDAO;
import com.metube.user.vo.securityUserVO;
import com.metube.user.vo.userVO;

public class CustomUserDetailsService implements UserDetailsService{

	@Resource(name = "userDAO")
	private userDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		securityUserVO user = null;
		try {
			user = userDAO.getUserById(userName);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(user == null) {
			throw new UsernameNotFoundException(userName);
		}

		return user;
	}


}