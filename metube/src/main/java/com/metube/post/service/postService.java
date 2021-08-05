package com.metube.post.service;

import java.util.List;

import com.metube.post.vo.postVO;

public interface postService {
	
	List<postVO> getPostList(postVO vo) throws Exception;

	int createPost(postVO vo) throws Exception;

	int deletePost(postVO vo) throws Exception;

	postVO detailPost(postVO vo) throws Exception;

	int is_deletePost(postVO vo) throws Exception;

	List<postVO> getNoticeList(postVO vo) throws Exception;

	int modifyPost(postVO vo) throws Exception;

	List<postVO> searchPostList(postVO vo) throws Exception;
	
	List<postVO> searchNoticeList(postVO vo) throws Exception;

	List<postVO> getUserPostList(postVO pvo) throws Exception;

	List<postVO> getUserCommunityList(postVO pvo) throws Exception;

	postVO detailNotice(postVO vo) throws Exception;

	int update_view(postVO vo) throws Exception;

	int createNotice(postVO vo) throws Exception;
}

