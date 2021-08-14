package com.metube.user.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.metube.post.vo.postVO;
import com.metube.sub.vo.subVO;
import com.metube.user.vo.userVO;

public interface userService {
	
	List<userVO> getUserList(userVO vo) throws Exception;
	
	boolean loginCheck(userVO vo, HttpSession session) throws Exception;
	
	userVO noPwUser(userVO vo) throws Exception;
	
	void logout(HttpSession session) throws Exception;
	
	int signUp(userVO vo) throws Exception;

	userVO getUser(userVO vo) throws Exception;

	int userLock(userVO vo) throws Exception;

	int withdrawal(userVO vo, HttpSession session) throws Exception;

	Map<String, Object> getUserDetail(userVO vo, postVO pvo, subVO svo) throws Exception;

	List<userVO> nameGetUser(userVO vo) throws Exception;
}
