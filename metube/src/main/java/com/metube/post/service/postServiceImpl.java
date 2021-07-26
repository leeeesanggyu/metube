package com.metube.post.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

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
		return postDAO.getPostList(vo);
	}

	@Override
	public int createPost(postVO vo) throws Exception {
		return postDAO.createPost(vo);
	}

	@Override
	public int deletePost(postVO vo) throws Exception {
		return postDAO.deletePost(vo);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public postVO detailPost(postVO vo) throws Exception {
		postDAO.update_view(vo);
		return postDAO.selectOne(vo);
	}

	@Override
	public int is_deletePost(postVO vo) throws Exception {
		return postDAO.is_deletePost(vo);
	}

	@Override
	public List<postVO> getNoticeList(postVO vo) throws Exception {
		return postDAO.getNoticeList(vo);
	}

	@Override
	public int modifyPost(postVO vo) throws Exception {
		return postDAO.modifyPost(vo);
	}

}
