package com.metube.like.service;

import javax.servlet.http.HttpSession;

import com.metube.like.vo.likeVO;
import com.metube.sub.vo.subVO;

public interface likeService {

	int post_like(likeVO vo) throws Exception;

	int like_delete(likeVO vo) throws Exception;

	likeVO is_like(int post_pk, HttpSession session) throws Exception;
}
