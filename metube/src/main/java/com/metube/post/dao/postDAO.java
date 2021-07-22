package com.metube.post.dao;

import java.util.List;

import com.metube.post.vo.postVO;

public interface postDAO {
	
	List<postVO> getPostList(postVO vo) throws Exception;

	int createPost(postVO vo) throws Exception;

	int deletePost(postVO vo) throws Exception;

	postVO selectOne(postVO vo) throws Exception;

	int update_view(postVO vo) throws Exception;

	int is_deletePost(postVO vo) throws Exception;
}

