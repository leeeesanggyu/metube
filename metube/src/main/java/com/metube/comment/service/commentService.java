package com.metube.comment.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.metube.comment.vo.commentVO;

public interface commentService {

	int createComment(commentVO vo, HttpSession session) throws Exception;

	int deleteComment(commentVO vo) throws Exception;

	
}
