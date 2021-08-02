package com.metube.like.service;

import com.metube.like.vo.likeVO;

public interface likeService {

	int post_like(likeVO vo) throws Exception;

	int post_like_count(likeVO lvo) throws Exception;

}
