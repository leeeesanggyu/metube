package com.metube.post.service;

import java.util.List;

import com.metube.post.vo.postVO;

public interface postService {
	
	List<postVO> getPostList(postVO vo) throws Exception;

	int createPost(postVO vo) throws Exception;

	int deletePost(postVO vo) throws Exception;
}

