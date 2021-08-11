package com.metube.post.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.metube.post.vo.postVO;

public interface postService {
	
	int createPost(postVO vo) throws Exception;

	int deletePost(postVO vo) throws Exception;

	Map<String, Object> detailPost(int post_pk, HttpSession session, String arg) throws Exception;

	int is_deletePost(postVO vo) throws Exception;

	int modifyPost(postVO vo) throws Exception;

	List<postVO> searchNoticeList(postVO vo) throws Exception;

	postVO detailNotice(int post_pk) throws Exception;

	int update_view(postVO vo) throws Exception;

	int createNotice(postVO vo) throws Exception;

	Map<String, Object> getPostNotice() throws Exception;

	Map<String, Object> searchUserPost(String search_arg) throws Exception;

	Map<String, Object> getNoticeList() throws Exception;
}

