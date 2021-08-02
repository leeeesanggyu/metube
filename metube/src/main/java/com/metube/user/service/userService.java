package com.metube.user.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.metube.user.vo.userVO;

public interface userService {
	
	List<userVO> getUserList(userVO vo) throws Exception;
	
	boolean loginCheck(userVO vo, HttpSession session) throws Exception;
	
	userVO noPwUser(userVO vo) throws Exception;
	
	void logout(HttpSession session) throws Exception;
	
	int signUp(userVO vo) throws Exception;

	userVO getUser(userVO vo) throws Exception;

	List<userVO> nameGetUser(userVO vo) throws Exception;

	int userLock(userVO vo) throws Exception;

	int withdrawal(userVO vo) throws Exception;
}
