package com.metube.post.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.metube.comment.dao.commentDAO;
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

	@Override
	public postVO detailPost(postVO vo) throws Exception {
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

	@Override
	public List<postVO> searchPostList(postVO vo) throws Exception {
		return postDAO.searchPostList(vo);
	}

	@Override
	public List<postVO> searchNoticeList(postVO vo) throws Exception {
		return postDAO.searchNoticeList(vo);
	}

	@Override
	public List<postVO> getUserPostList(postVO pvo) throws Exception {
		return postDAO.userPostList(pvo);
	}

	@Override
	public List<postVO> getUserCommunityList(postVO pvo) throws Exception {
		return postDAO.userCommunityList(pvo);
	}

	@Override
	public postVO detailNotice(postVO vo) throws Exception {
		return postDAO.detailNotice(vo);
	}

	@Override
	public int update_view(postVO vo) throws Exception {
		return postDAO.update_view(vo);
	}

	@Override
	public int createNotice(postVO vo) throws Exception {
		return postDAO.createNotice(vo);
	}

}
