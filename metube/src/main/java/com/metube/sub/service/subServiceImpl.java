package com.metube.sub.service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

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

	@Override
	public subVO getSub(int user_pk, HttpSession session) throws Exception {
		subVO vo = new subVO();
		vo.setP_user_pk(user_pk);
		vo.setC_user_pk((int)session.getAttribute("user_pk"));
		return subDAO.getSub(vo);
	}
	
	
}
