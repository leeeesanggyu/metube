package com.metube.user.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.metube.user.service.userService;
import com.metube.user.vo.userVO;

@Controller
public class userController {

	@Resource(name = "UserService")
	private userService userService;
	
	/**
	 * Test Controller
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/mainTest.do")
	public String mainTest(ModelMap model) throws Exception {
		System.out.println("main controller");
		int num = userService.onePlus(3);
		
		model.addAttribute("result", num);
		return "test";
	}
	
	/**
	 * 유저 전체 목록을 조회한다
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getUserList.do")
	public String getUserList(userVO vo, Model model) throws Exception {
		System.out.println("userController - getUserList");
		model.addAttribute("user", userService.getUserList(vo));
		return "userList";
	}
	
	/**
	 * 로그인 페이지로 이동한다
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/login.do")
	public String userLogin() throws Exception {
		return "login";
	}
	
	/**
	 * 로그인 하는 로직
	 * @param vo
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/loginCheck.do")
	public ModelAndView loginCheck(@ModelAttribute userVO vo, HttpSession session) throws Exception {
		System.out.println("userController - loginCheck");
		boolean result = userService.loginCheck(vo, session);
		
		ModelAndView mv = new ModelAndView();
		if(result) {
			mv.setViewName("main");
			mv.addObject("msg", "success");
		}
		else {
			mv.setViewName("login");
			mv.addObject("msg", "fail");
		}
		return mv;
	}
	
	/**
	 * 로그아웃
	 * @param vo
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/logout.do")
	public ModelAndView logout(HttpSession session) throws Exception {
		System.out.println("userController - logout");
		
		userService.logout(session);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		mv.addObject("msg", "logout");
		return mv;
	}
	
	@RequestMapping(value="/signUpPage.do")
	public String signUpPage() throws Exception {
		return "signUp";
	}
	
	@RequestMapping(value="/signUp.do")
	public ModelAndView signUp(@ModelAttribute userVO vo) throws Exception {
		System.out.println("userController - signUp");
		userService.signUp(vo);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		return mv;
	}
}
