package com.metube.user.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metube.common.hash.BCrypt;
import com.metube.user.dao.userDAO;
import com.metube.user.vo.userVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("UserService")
public class userServiceImpl implements userService{

	@Resource(name = "userDAO")
	private userDAO user_DAO;
	
	@Override
	public List<userVO> getUserList(userVO vo) throws Exception {
		return user_DAO.getUserList(vo);
	}

	@Override
	public boolean loginCheck(userVO vo, HttpSession session) throws Exception {
		userVO hash_password = user_DAO.loginCheck(vo);	//해쉬된 비밀번호 찾기

		if(BCrypt.checkpw(vo.getPassword(),hash_password.getPassword())) {
			System.out.println("비밀번호 비교 결과: 같음");
			//session 등록
			session.setAttribute("user_pk", hash_password.getPk());
			session.setAttribute("email", hash_password.getEmail());
			session.setAttribute("name", hash_password.getName());
			session.setAttribute("role", hash_password.getRole());
			return true;
		}
		System.out.println("비밀번호 비교 결과: 다름");
		return false;
	}

	@Override
	public userVO noPwUser(userVO vo) throws Exception {
		return user_DAO.noPwUser(vo);
	}
	
	@Override
	public void logout(HttpSession session) throws Exception {
		//세션 변수 개별 삭제
		//session.removeAttribute("email");
		//세션 전체 초기화
		session.invalidate();
	}

	@Override
	public int signUp(userVO vo) throws Exception {
		return user_DAO.signUp(vo);
	}

	@Override
	public userVO getUser(userVO vo, HttpSession session) throws Exception {
		vo.setEmail((String)session.getAttribute("email"));
		return user_DAO.loginCheck(vo);
	}

	@Override
	public List<userVO> nameGetUser(userVO vo) throws Exception {
		return user_DAO.nameGetUser(vo);
	}

	
}
