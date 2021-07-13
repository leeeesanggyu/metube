package com.metube.comment.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.metube.comment.service.commentService;
import com.metube.comment.vo.commentVO;

@Controller
public class commentController {

	@Resource(name = "CommentService")
	private commentService commentService;
	
	@RequestMapping(value="/createComment.do")
	public ModelAndView createComment(@ModelAttribute commentVO vo) throws Exception {
		System.out.println("commentController - createComment");
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("getPost");
		mv.addObject("postList", commentService.createComment(vo));
		return mv;
	}
}
