package com.metube.post.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.metube.post.service.postService;
import com.metube.post.vo.postVO;
import com.metube.user.service.userService;
import com.metube.user.vo.userVO;

@Controller
public class postController {

	@Resource(name = "PostService")
	private postService postService;
	
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
		System.out.println("vo.getTitle : " + vo.getTitle());
		ModelAndView mv = new ModelAndView();
		mv.setViewName("getPost");
		mv.addObject("postList", postService.createPost(vo));
		return mv;
	}
	
	
}
