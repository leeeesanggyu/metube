package com.metube.user.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;

import com.metube.common.dao.mysqlAbstractMapper;
import com.metube.user.vo.userVO;


@Repository("userDAO")
public class userDAOImpl extends mysqlAbstractMapper implements userDAO{
	
	@Override
	public List<userVO> getUserList(userVO vo) throws Exception {
		System.out.println("userDAO - getUserList");
		return selectList("userDAO.selectUserList", vo);
	}

	@Override
	public boolean loginCheck(userVO vo) throws Exception {
		System.out.println("userDAO - loginCheck");
		userVO result = selectOne("userDAO.selectUserName", vo);
		return (result == null) ?false :true;
	}
	
	@Override
	public userVO noPwUser(userVO vo) throws Exception {
		System.out.println("userDAO - noPwUser");
		return selectOne("userDAO.noPwUser", vo);
	}

	@Override
	public int signUp(userVO vo) throws Exception {
		System.out.println("userDAO - signUp");
		return insert("userDAO.signUp", vo);
	}


	
}
