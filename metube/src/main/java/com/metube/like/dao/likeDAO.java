package com.metube.like.dao;

import com.metube.like.vo.likeVO;

public interface likeDAO {
	
	int post_like(likeVO vo) throws Exception;

	int post_like_count(likeVO lvo) throws Exception;

}
