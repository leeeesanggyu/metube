package com.metube.user.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.metube.hash.BCrypt;
import com.metube.post.vo.postVO;
import com.metube.user.service.userService;
import com.metube.user.vo.userVO;

@Controller
public class userController {

	@Resource(name = "UserService")
	private userService userService;
	
	/**
	 * 로그인 페이지로 이동한다.
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/login.do")
	public String userLogin() throws Exception {
		return "login";
	}
	
	/**
	 * 회원가입 페이지로 이동한다.
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/signUpPage.do")
	public String signUpPage() throws Exception {
		return "signUp";
	}
	
	/**
	 * 유저 전체 목록을 조회한다.
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/userList.do", method = RequestMethod.GET)
	public ModelAndView getUserList() throws Exception {
		System.out.println("userController - getUserList");
		userVO vo = new userVO();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("userList", userService.getUserList(vo));
		mv.setViewName("main");
		return mv;
	}
	
	/**
	 * 로그인
	 * @param vo email, password
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/loginCheck.do", method = RequestMethod.POST)
	public ModelAndView loginCheck(@ModelAttribute userVO vo, HttpSession session) throws Exception {
		System.out.println("userController - loginCheck");
		
		ModelAndView mv = new ModelAndView();
		if( userService.loginCheck(vo, session) ) {
			mv.setViewName("getPost");
		}
		else {
			mv.setViewName("login");
			mv.addObject("msg", "fail");
		}
		return mv;
	}
	
	/**
	 * 로그아웃을 정의한다.
	 * @param vo
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/logout.do", method = RequestMethod.GET)
	public ModelAndView logout(HttpSession session) throws Exception {
		System.out.println("userController - logout");
		userService.logout(session);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		mv.addObject("msg", "logout");
		return mv;
	}
	
	/**
	 * 회원가입을 정의한다.
	 * @param vo email, password, name, role
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/signUp.do", method = RequestMethod.POST)
	public ModelAndView signUp(@ModelAttribute userVO vo) throws Exception {
		System.out.println("userController - signUp");
		System.out.println("==========================================================================================");
		System.out.println("password: " + vo.getPassword());
		String hash_Password = BCrypt.hashpw(vo.getPassword(), BCrypt.gensalt());
		vo.setPassword(hash_Password);
		System.out.println("hash password: " + vo.getPassword());
		System.out.println("==========================================================================================");
		userService.signUp(vo);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		return mv;
	}
	
	/**
	 * 자신의 정보를 가져온다.
	 * @param vo
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/user.do", method = RequestMethod.GET)
	public ModelAndView getUser(HttpSession session) throws Exception {
		System.out.println("userController - getUser");
		userVO vo = new userVO();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("getUserPage");
		mv.addObject("userInfo", userService.getUser(vo, session));
		return mv;
	}
}
