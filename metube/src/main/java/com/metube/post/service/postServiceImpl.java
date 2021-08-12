package com.metube.post.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.metube.comment.dao.commentDAO;
import com.metube.comment.vo.commentVO;
import com.metube.post.dao.postDAO;
import com.metube.like.dao.likeDAO;
import com.metube.sub.dao.subDAO;
import com.metube.user.dao.userDAO;
import com.metube.user.vo.userVO;
import com.metube.post.vo.postVO;
import com.metube.sub.service.subService;
import com.metube.sub.vo.subVO;
import com.metube.like.service.likeService;
import com.metube.like.vo.likeVO;


@Service("PostService")
public class postServiceImpl implements postService{

	@Resource(name = "PostDAO")
	private postDAO postDAO;
	
	@Resource(name = "CommentDAO")
	private commentDAO commentDAO;
	
	@Resource(name = "userDAO")
	private userDAO userDAO;
	
	@Resource(name = "likeDAO")
	private likeDAO likeDAO;
	
	@Resource(name = "SubDAO")
	private subDAO subDAO;
	
	@Resource(name = "SubService")
	private subService subService;
	
	@Resource(name = "LikeService")
	private likeService likeService;
	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public Map<String, Object> detailPost(
			int post_pk, HttpSession session, String arg
	) throws Exception {
		postVO vo = new postVO();
		vo.setPk(post_pk);
		postVO post_result = detailNotice(post_pk);

		commentVO cvo = new commentVO();
		cvo.setPost_pk(post_pk);
		
		subVO svo = new subVO();
		svo.setP_user_pk(post_result.getUser_pk());
		
		likeVO lvo = new likeVO();
		lvo.setPost_pk(post_pk);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("comment", commentDAO.getComment(cvo));
		map.put("sub", subService.getSub(post_result.getUser_pk(), session));		
		map.put("sub_count", subDAO.sub_count(svo));
		map.put("post_like", likeDAO.post_like_count(lvo));
		map.put("is_like", likeService.is_like(post_pk, session));
		
		if(arg.equals("normal")) {
			map.put("post", postDAO.selectOne(vo));
		}
		else if(arg.equals("notice")) {
			map.put("post", postDAO.detailNotice(vo));
		}
		return map;
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public  Map<String, Object> getPostNotice() throws Exception {
		postVO vo = new postVO();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("postList", postDAO.getPostList(vo));
		map.put("noticeList", postDAO.getNoticeList(vo));
		return map;
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public Map<String, Object> getNoticeList() throws Exception {
		postVO vo = new postVO();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("noticeList", postDAO.getNoticeList(vo));
		return map;
	}
	
	@Override
	public postVO detailNotice(int post_pk) throws Exception {
		postVO vo = new postVO();
		vo.setPk(post_pk);
		return postDAO.detailNotice(vo);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public Map<String, Object> searchUserPost(String search_arg) throws Exception {
		postVO vo = new postVO();
		vo.setTitle(search_arg);
		
		userVO uvo = new userVO();
		uvo.setName(search_arg);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userList", userDAO.nameGetUser(uvo));
		map.put("postList", postDAO.searchPostList(vo));
		return map;
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
	public int is_deletePost(postVO vo) throws Exception {
		return postDAO.is_deletePost(vo);
	}

	@Override
	public int modifyPost(postVO vo) throws Exception {
		return postDAO.modifyPost(vo);
	}

	@Override
	public List<postVO> searchNoticeList(postVO vo) throws Exception {
		return postDAO.searchNoticeList(vo);
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
