package com.metube.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.metube.post.vo.postVO;

@Controller
public class commonController {

	@RequestMapping(value="/post/goHome.do")
	public ModelAndView goHome(postVO vo) throws Exception {
		System.out.println("commonController - goHome");
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("getPost");
		return mv;
	}
}
