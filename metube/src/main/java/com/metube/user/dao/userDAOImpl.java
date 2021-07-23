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
		return selectList("userDAO.selectUserList", vo);
	}

	@Override
	public userVO loginCheck(userVO vo) throws Exception {
		return selectOne("userDAO.getUser", vo);
	}
	
	@Override
	public userVO noPwUser(userVO vo) throws Exception {
		return selectOne("userDAO.noPwUser", vo);
	}

	@Override
	public int signUp(userVO vo) throws Exception {
		return insert("userDAO.signUp", vo);
	}

	@Override
	public List<userVO> nameGetUser(userVO vo) throws Exception {
		return selectList("userDAO.nameGetUser", vo);
	}

	@Override
	public int userLock(userVO vo) throws Exception {
		return update("userDAO.userLock", vo);
	}

	@Override
	public int withdrawal(userVO vo) throws Exception {
		return delete("userDAO.withdrawal", vo);
	}
	
}
