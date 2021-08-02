package com.metube.like.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.metube.like.dao.likeDAO;
import com.metube.like.vo.likeVO;

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

}
