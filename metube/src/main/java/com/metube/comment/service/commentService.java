package com.metube.comment.service;

import java.util.List;

import com.metube.comment.vo.commentVO;

public interface commentService {

	int createComment(commentVO vo) throws Exception;

	List<commentVO> getComment(commentVO vo) throws Exception;

	
}
