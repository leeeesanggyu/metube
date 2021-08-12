package com.metube.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.metube.common.hash.BCrypt;
import com.metube.post.dao.postDAO;
import com.metube.post.service.postService;
import com.metube.post.vo.postVO;
import com.metube.sub.service.subService;
import com.metube.sub.vo.subVO;
import com.metube.user.dao.userDAO;
import com.metube.sub.dao.subDAO;

import com.metube.user.vo.userVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("UserService")
public class userServiceImpl implements userService{

	@Resource(name = "userDAO")
	private userDAO userDAO;
	
	@Resource(name = "PostDAO")
	private postDAO postDAO;
	
	@Resource(name = "SubDAO")
	private subDAO subDAO;
	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public boolean loginCheck(userVO vo, HttpSession session) throws Exception {
		userVO hash_password = userDAO.loginCheck(vo);	//해쉬된 비밀번호 찾기

		if(BCrypt.checkpw(vo.getPassword(),hash_password.getPassword())) {
			System.out.println("비밀번호 비교 결과: 같음");
			//session 등록
			session.setAttribute("user_pk", hash_password.getPk());
			session.setAttribute("email", hash_password.getEmail());
			session.setAttribute("name", hash_password.getName());
			session.setAttribute("role", hash_password.getRole());
			session.setAttribute("lock", hash_password.getLock());

			return true;
		}
		System.out.println("비밀번호 비교 결과: 다름");
		return false;
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
		String hash_Password = BCrypt.hashpw(vo.getPassword(), BCrypt.gensalt());
		vo.setPassword(hash_Password);
		return userDAO.signUp(vo);
	}
	
	@Override
	public List<userVO> nameGetUser(userVO vo) throws Exception {
		return userDAO.nameGetUser(vo);
	}
	
	@Override
	public Map<String, Object> getUserDetail(userVO vo, postVO pvo, subVO svo) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userInfo", getUser(vo));
		map.put("postList", postDAO.userPostList(pvo));
		map.put("communityList", postDAO.userCommunityList(pvo));
		map.put("sub_count", subDAO.sub_count(svo));
		return map;
	}
	
	@Override
	public int withdrawal(userVO vo, HttpSession session) throws Exception {
		logout(session);
		return userDAO.withdrawal(vo);
	}

	@Override
	public userVO getUser(userVO vo) throws Exception {
		return userDAO.getUser(vo);
	}


	@Override
	public int userLock(userVO vo) throws Exception {
		return userDAO.userLock(vo);
	}

	@Override
	public userVO noPwUser(userVO vo) throws Exception {
		return userDAO.noPwUser(vo);
	}
	
	@Override
	public List<userVO> getUserList(userVO vo) throws Exception {
		return userDAO.getUserList(vo);
	}

	


	
	
	

	
}
