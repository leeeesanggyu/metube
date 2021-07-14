package com.metube.post.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.metube.comment.dao.commentDAO;
import com.metube.comment.vo.commentVO;
import com.metube.post.dao.postDAO;
import com.metube.post.vo.postVO;

@Service("PostService")
public class postServiceImpl implements postService{

	@Resource(name = "PostDAO")
	private postDAO postDAO;
	
	@Resource(name = "CommentDAO")
	private commentDAO commentDAO;
	
	@Override
	public List<postVO> getPostList(postVO vo) throws Exception {
		System.out.println("postService - getPostList");
		return postDAO.getPostList(vo);
	}

	@Override
	public int createPost(postVO vo) throws Exception {
		System.out.println("postService - createPost");
		return postDAO.createPost(vo);
	}

	@Override
	public int deletePost(postVO vo) throws Exception {
		System.out.println("postService - deletePost");
		
		commentVO comment_vo = new commentVO();
		comment_vo.setPost_pk(vo.getPk());
		commentDAO.deletePostComment(comment_vo);
		return postDAO.deletePost(vo);
	}

	@Override
	public postVO detailPost(postVO vo) throws Exception {
		return postDAO.selectOne(vo);
	}

}
