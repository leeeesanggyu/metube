package com.metube.post.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.metube.post.service.postService;
import com.metube.post.vo.postVO;
import com.metube.sub.service.subService;
import com.metube.sub.vo.subVO;
import com.metube.user.service.userService;
import com.metube.user.vo.userVO;
import com.metube.comment.service.commentService;
import com.metube.comment.vo.commentVO;
import com.metube.like.service.likeService;
import com.metube.like.vo.likeVO;

@Controller
@RequestMapping(value="/post")
public class postController {

	@Resource(name = "PostService")
	private postService postService;
	
	@Resource(name = "CommentService")
	private commentService commentService;
	
	@Resource(name = "SubService")
	private subService subService;
	
	@Resource(name = "UserService")
	private userService userService;
	
	@Resource(name = "LikeService")
	private likeService likeService;
	
	/**
	 * 공지사항 작성 페이지로 간다.
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goNoticeCreate")
	public ModelAndView GoNoticeCreate() throws Exception {
		try {
			ModelAndView mv = new ModelAndView();
			mv.setViewName("createNotice");
			return mv;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 공지사항을 가져온다.
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/notice/list")
	public ModelAndView GetNoticeList() throws Exception {
		try {
			postVO vo = new postVO();
			ModelAndView mv = new ModelAndView();
			mv.setViewName("noticePost");
			mv.addObject("noticeList", postService.getNoticeList(vo));
			return mv;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 공지사항 디테일을 가져온다.
	 * @param post_pk
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/notice/detail/{post_pk}", method = RequestMethod.GET)
	public ModelAndView DetailNotice(
			@PathVariable("post_pk") int post_pk, HttpSession session
	) throws Exception {
		try {
			postVO vo = new postVO();
			vo.setPk(post_pk);
			
			commentVO comment_vo = new commentVO();
			comment_vo.setPost_pk(post_pk);
			
			postVO post_result = postService.detailNotice(vo);
			
			subVO subvo = new subVO();
			subvo.setP_user_pk(post_result.getUser_pk());
			
			likeVO lvo = new likeVO();
			lvo.setPost_pk(post_pk);
			
			ModelAndView mv = new ModelAndView();
			mv.addObject("post", post_result);
			mv.addObject("comment", commentService.getComment(comment_vo));
			mv.addObject("sub", subService.getSub(post_result.getUser_pk(), session));
			mv.addObject("sub_count", subService.sub_count(subvo));
			mv.addObject("post_like", likeService.post_like_count(lvo));
			
			mv.addObject("is_like", likeService.is_like(post_pk, session));

			
			mv.setViewName("detailPost");
			return mv;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 게시물 생성 페이지로 간다.
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goCreate")
	public ModelAndView GoCreatePost() throws Exception {
		try {
			ModelAndView mv = new ModelAndView();
			mv.setViewName("createPost");
			return mv;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 게시물 목록을 가져온다.
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public ModelAndView GetPostList() throws Exception {
		try {
			postVO vo = new postVO();
			ModelAndView mv = new ModelAndView();
			mv.setViewName("main");
			mv.addObject("postList", postService.getPostList(vo));
			mv.addObject("noticeList", postService.getNoticeList(vo));
			return mv;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 게시물과 유저를 검색한다.
	 * @param title
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/search/{search_arg}", method = RequestMethod.GET)
	public ModelAndView SearchPostList(
			@PathVariable("search_arg") String search_arg
	) throws Exception {
		try {
			postVO vo = new postVO();
			vo.setTitle(search_arg);
			
			userVO uvo = new userVO();
			uvo.setName(search_arg);
			
			ModelAndView mv = new ModelAndView();
			mv.setViewName("searchList");
			mv.addObject("userList", userService.nameGetUser(uvo));
			mv.addObject("postList", postService.searchPostList(vo));

			return mv;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 상세 게시물을 불러온다.
	 * @param post_pk
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/detail/{post_pk}", method = RequestMethod.GET)
	public ModelAndView DetailPost(
			@PathVariable("post_pk") int post_pk, HttpSession session
	) throws Exception {
		try {
			postVO vo = new postVO();
			vo.setPk(post_pk);
			
			commentVO comment_vo = new commentVO();
			comment_vo.setPost_pk(post_pk);
			
			postVO post_result = postService.detailPost(vo);
			
			subVO subvo = new subVO();
			subvo.setP_user_pk(post_result.getUser_pk());
			
			likeVO lvo = new likeVO();
			lvo.setPost_pk(post_pk);
			
			ModelAndView mv = new ModelAndView();
			mv.addObject("post", post_result);
			mv.addObject("comment", commentService.getComment(comment_vo));
			mv.addObject("sub", subService.getSub(post_result.getUser_pk(), session));
			mv.addObject("sub_count", subService.sub_count(subvo));
			mv.addObject("post_like", likeService.post_like_count(lvo));
			
			mv.addObject("is_like", likeService.is_like(post_pk, session));

			
			mv.setViewName("detailPost");
			return mv;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 게시물 수정 페이지로 간다.
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goModify/{post_pk}")
	public ModelAndView GoModifyPost(@PathVariable("post_pk") int post_pk) throws Exception {
		try {
			ModelAndView mv = new ModelAndView();
			postVO vo = new postVO();
			vo.setPk(post_pk);
			System.out.println(vo.getPk());
			
			mv.setViewName("modifyPost");
			mv.addObject("post", postService.detailNotice(vo));
			return mv;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
