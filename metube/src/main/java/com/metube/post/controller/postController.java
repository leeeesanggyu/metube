package com.metube.post.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
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
import com.metube.common.service.commonService;


@Controller
@RequestMapping(value="/post")
public class postController {

	@Resource(name = "PostService")
	private postService postService;
	
	@Resource(name = "CommentService")
	private commentService commentService;
	
	
	@Resource(name = "UserService")
	private userService userService;
	
	@Resource(name = "LikeService")
	private likeService likeService;
	
	@Resource(name = "CommonService")
	private commonService commonService;
	
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
	 * 게시물 수정 페이지로 간다.
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goModify/{post_pk}")
	public ModelAndView GoModifyPost(@PathVariable("post_pk") int post_pk) throws Exception {
		try {
			ModelAndView mv = new ModelAndView();
			mv.setViewName("modifyPost");
			mv.addObject("post", postService.detailNotice(post_pk));
			return mv;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 게시물 목록을 가져온다.(post, notice)
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public ModelAndView GetPostList() throws Exception {
		try {
			ModelAndView mv = new ModelAndView();
			mv.setViewName("main");
			mv.addObject("data", postService.getPostNotice());
			return mv;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 공지사항 리스트를 가져온다.
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/notice/list")
	public ModelAndView GetNoticeList() throws Exception {
		try {
			ModelAndView mv = new ModelAndView();
			mv.setViewName("noticePost");
			mv.addObject("data", postService.getNoticeList());
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
	@RequestMapping(value="/search/{search_arg}")
	public ModelAndView SearchUserPost(
			@PathVariable("search_arg") String search_arg
	) throws Exception {
		try {
			ModelAndView mv = new ModelAndView();
			mv.setViewName("searchList");
			mv.addObject("data", postService.searchUserPost(search_arg));
			return mv;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 상세 게시물을 가져온다.
	 * @param post_pk
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{post_pk}/{arg}")
	public ModelAndView DetailPost(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("post_pk") int post_pk, 
			@PathVariable("arg") String arg, 
			HttpSession session
	) throws Exception {
		try {
			Cookie cookie = commonService.view_count_check(request.getCookies(), post_pk);
			if(cookie != null) {
				response.addCookie(cookie);
				postVO vo = new postVO();
				vo.setPk(post_pk);
		        postService.update_view(vo);
			}
			
			ModelAndView mv = new ModelAndView();
			mv.setViewName("detailPost");
			mv.addObject("detailPost", postService.detailPost(post_pk, session, arg));
		   
			return mv;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
