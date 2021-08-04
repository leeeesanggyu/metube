package com.metube.sub.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.metube.post.vo.postVO;
import com.metube.sub.dao.subDAO;
import com.metube.sub.vo.subVO;

@Service("SubService")
public class subServiceImpl implements subService{

	@Resource(name = "SubDAO")
	private subDAO subDAO;

	@Override
	public int sub_add(subVO vo) throws Exception {
		return subDAO.sub_add(vo);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public subVO getSub(int user_pk, HttpSession session) throws Exception {
		subVO vo = new subVO();
		vo.setP_user_pk(user_pk);
		vo.setC_user_pk((int)session.getAttribute("user_pk"));
		return subDAO.getSub(vo);
	}

	@Override
	public int sub_count(subVO vo) throws Exception {
		return subDAO.sub_count(vo);
	}

	@Override
	public int sub_delete(subVO vo) throws Exception {
		return subDAO.sub_delete(vo);
	}

	@Override
	public List<postVO> getSubPostList(subVO vo) throws Exception {
		return subDAO.getSubPost(vo);
	}
	
	
}
