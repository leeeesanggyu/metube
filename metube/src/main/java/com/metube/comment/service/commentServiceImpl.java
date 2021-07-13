package com.metube.comment.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.metube.comment.dao.commentDAO;
import com.metube.comment.vo.commentVO;
import com.metube.post.vo.postVO;

@Service("CommentService")
public class commentServiceImpl implements commentService{

	@Resource(name = "CommentDAO")
	private commentDAO commentDAO;
	
	@Override
	public int createComment(commentVO vo) throws Exception {
		return commentDAO.createComment(vo);
	}

	@Override
	public List<commentVO> getComment(commentVO vo) throws Exception {
		return commentDAO.getComment(vo);
	}

	
}
