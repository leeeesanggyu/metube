package com.metube.comment.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.metube.comment.dao.commentDAO;
import com.metube.comment.vo.commentVO;
import com.metube.post.vo.postVO;

@Service("CommentService")
public class commentServiceImpl implements commentService{

	@Resource(name = "CommentDAO")
	private commentDAO commentDAO;
	
	@Override
	public int createComment(commentVO vo, HttpSession session) throws Exception {
		vo.setUser_pk((int)session.getAttribute("user_pk"));
		return commentDAO.createComment(vo);
	}

	@Override
	public List<commentVO> getComment(commentVO vo) throws Exception {
		return commentDAO.getComment(vo);
	}

	@Override
	public int deleteComment(commentVO vo) throws Exception {
		return commentDAO.deleteComment(vo);
	}

	
}
