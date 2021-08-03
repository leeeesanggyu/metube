package com.metube.like.dao;

import com.metube.like.vo.likeVO;
import com.metube.sub.vo.subVO;

public interface likeDAO {
	
	int post_like(likeVO vo) throws Exception;

	int post_like_count(likeVO lvo) throws Exception;

	int like_delete(likeVO vo) throws Exception;

	likeVO is_like(likeVO vo) throws Exception;

}
