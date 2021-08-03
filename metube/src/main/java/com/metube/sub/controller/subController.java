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
	
	/**
	 * 구독 한다.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public boolean sub_add(@RequestBody subVO vo) throws Exception {
		try {
			subService.sub_add(vo);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 구독을 삭제한다.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/del", method=RequestMethod.DELETE)
	public boolean Sub_Delete(@RequestBody subVO vo) throws Exception {
		try {
			subService.sub_delete(vo);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RequestMapping(value="/goSubPost")
	public ModelAndView GoSubPost(HttpSession session) throws Exception {
		try {
			subVO vo = new subVO();
			vo.setC_user_pk((int)session.getAttribute("user_pk"));
			
			ModelAndView mv = new ModelAndView();
			mv.setViewName("subPost");
			mv.addObject("subPostList", subService.getSubPostList(vo));
			return mv;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

