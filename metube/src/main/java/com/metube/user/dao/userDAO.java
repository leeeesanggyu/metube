package com.metube.user.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.metube.user.vo.userVO;

public interface userDAO {
	
	List<userVO> getUserList(userVO vo) throws Exception;
	
	boolean loginCheck(userVO vo) throws Exception;
	
	userVO noPwUser(userVO vo) throws Exception;
		
	int signUp(userVO vo) throws Exception;
}
