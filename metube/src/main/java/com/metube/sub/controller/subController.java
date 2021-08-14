package com.metube.sub.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.metube.post.vo.postVO;
import com.metube.sub.service.subService;
import com.metube.sub.vo.subVO;

@Controller
@RequestMapping(value="/sub")
public class subController {

	@Resource(name = "SubService")
	private subService subService;
	
	@RequestMapping(value="/goSubPost")
	public ModelAndView GoSubPost(HttpSession session) throws Exception {
		try {
			ModelAndView mv = new ModelAndView();
			mv.setViewName("subPost");
			mv.addObject("subPostList", subService.getSubPostList(session));
			return mv;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

