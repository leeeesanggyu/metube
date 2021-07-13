package com.metube.post.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.metube.post.service.postService;
import com.metube.post.vo.postVO;
import com.metube.user.service.userService;
import com.metube.user.vo.userVO;
import com.metube.comment.service.commentService;
import com.metube.comment.vo.commentVO;

@Controller
public class postController {

	@Resource(name = "PostService")
	private postService postService;
	
	@Resource(name = "CommentService")
	private commentService commentService;
	
	@RequestMapping(value="/getPostList.do")
	public ModelAndView getPostList(postVO vo) throws Exception {
		System.out.println("postController - getPostList");
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main");
		mv.addObject("postList", postService.getPostList(vo));
		return mv;
	}
	
	@RequestMapping(value="/goCreatePost.do")
	public ModelAndView goCreatePost() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("createPost");
		return mv;
	}
	
	@RequestMapping(value="/createPost.do")
	public ModelAndView createPost(@ModelAttribute postVO vo) throws Exception {
		System.out.println("postController - createPost");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("getPost");
		mv.addObject("postList", postService.createPost(vo));
		return mv;
	}
	
	@RequestMapping(value="/deletePost_confirm.do", method = RequestMethod.GET)
	public ModelAndView deletePost_confirm(int user_pk, int post_pk, int role) throws Exception {
		System.out.println("postController - deletePost_confirm");
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("deletePost_confirm");
		mv.addObject("user_pk", user_pk);
		mv.addObject("post_pk", post_pk);
		mv.addObject("role", role);

		return mv;
	}
	
	@RequestMapping(value="/deletePost.do", method = RequestMethod.GET)
	public ModelAndView deletePost(int pk) throws Exception {
		System.out.println("postController - deletePost");
		postVO vo = new postVO();
		vo.setPk(pk);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("getPost");
		mv.addObject("postList", postService.deletePost(vo));
		return mv;
	}
	
	@RequestMapping(value="/detailPost.do", method = RequestMethod.GET)
	public ModelAndView detailPost(int post_pk) throws Exception {
		System.out.println("postController - detailPost");
		
		postVO vo = new postVO();
		vo.setPk(post_pk);
		
		commentVO comment_vo = new commentVO();
		comment_vo.setPost_pk(post_pk);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("detailPost");
		mv.addObject("post", postService.detailPost(vo));
		mv.addObject("comment", commentService.getComment(comment_vo));

		return mv;
	}
	
	
}
