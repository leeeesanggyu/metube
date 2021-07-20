package com.metube.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.metube.common.hash.BCrypt;
import com.metube.post.vo.postVO;
import com.metube.user.service.userService;
import com.metube.user.vo.userVO;

@Controller
@RequestMapping(value="/user")
public class userController {

	@Resource(name = "UserService")
	private userService userService;
	
	/**
	 * main 페이지로 이동한다.
	 */
	@RequestMapping(value="/main")
	public String goMain() throws Exception {
		return "main";
	}
	
	/**
	 * 로그인 페이지로 이동한다.
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/login")
	public String guUserLogin() throws Exception {
		return "login";
	}
	
	/**
	 * 회원가입 페이지로 이동한다.
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goSignUp")
	public String goSignUpPage() throws Exception {
		return "signUp";
	}
	
	/**
	 * 유저 전체 목록을 조회한다.
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/list", method = RequestMethod.GET)
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
	@ResponseBody
	@RequestMapping(value="/check", method = RequestMethod.POST)
	public boolean loginCheck(
			@RequestBody userVO vo, HttpSession session
	) throws Exception {
		System.out.println("userController - loginCheck");		
		try {		
			if(userService.loginCheck(vo, session) == true) {
				return true;
			}
			return false;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 로그아웃을 정의한다.
	 * @param vo
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public boolean logout(HttpSession session) throws Exception {
		System.out.println("userController - logout");
		userService.logout(session);
		return true;
	}
	
	/**
	 * 회원가입을 정의한다.
	 * @param vo email, password, name, role
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/sign", method = RequestMethod.POST)
	public boolean signUp(@RequestBody userVO vo) throws Exception {
		try {
			System.out.println("userController - signUp");
			System.out.println("==========================================================================================");
			System.out.println("password: " + vo.getPassword());
			String hash_Password = BCrypt.hashpw(vo.getPassword(), BCrypt.gensalt());
			vo.setPassword(hash_Password);
			System.out.println("hash password: " + vo.getPassword());
			System.out.println("==========================================================================================");
			userService.signUp(vo);
			
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 자신의 정보를 가져온다.
	 * @param vo
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/detail", method = RequestMethod.GET)
	public ModelAndView getUser(HttpSession session) throws Exception {
		System.out.println("userController - getUser");
		userVO vo = new userVO();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("getUserPage");
		mv.addObject("userInfo", userService.getUser(vo, session));
		return mv;
	}
}
