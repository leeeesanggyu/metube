package com.metube.post.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.metube.post.dao.postDAO;
import com.metube.post.vo.postVO;

@Service("PostService")
public class postServiceImpl implements postService{

	@Resource(name = "PostDAO")
	private postDAO postDAO;
	
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

}
