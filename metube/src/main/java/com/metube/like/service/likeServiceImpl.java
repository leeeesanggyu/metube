package com.metube.like.service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.metube.like.dao.likeDAO;
import com.metube.like.vo.likeVO;
import com.metube.sub.vo.subVO;

@Service("LikeService")
public class likeServiceImpl implements likeService{

	@Resource(name = "likeDAO")
	private likeDAO likeDAO;
	
	@Override
	public int post_like(likeVO vo) throws Exception {
		return likeDAO.post_like(vo);
	}

	@Override
	public int post_like_count(likeVO lvo) throws Exception {
		return likeDAO.post_like_count(lvo);
	}

	@Override
	public int like_delete(likeVO vo) throws Exception {
		return likeDAO.like_delete(vo);
	}

	@Override
	public likeVO is_like(int post_pk, HttpSession session) throws Exception {
		likeVO vo = new likeVO();
		vo.setPost_pk(post_pk);
		vo.setUser_pk((int)session.getAttribute("user_pk"));
		return likeDAO.is_like(vo);
	}

}
