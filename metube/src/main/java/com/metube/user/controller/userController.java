package com.metube.user.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.metube.common.hash.BCrypt;
import com.metube.post.service.postService;
import com.metube.post.vo.postVO;
import com.metube.sub.service.subService;
import com.metube.sub.vo.subVO;
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
	public String goUserLogin() throws Exception {
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
	 * 회원 검색 페이지로 이동한다.
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/search")
	public String goUserSearch() throws Exception {
		return "userSearch";
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
		try {
			userVO vo = new userVO();
			
			ModelAndView mv = new ModelAndView();
			mv.addObject("userList", userService.getUserList(vo));
			mv.setViewName("userList");
			return mv;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 유저 디테일을 가져온다.
	 * @param user_pk
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/detail/{user_pk}", method = RequestMethod.GET)
	public ModelAndView getUserDetail(
			@PathVariable("user_pk") int user_pk
	) throws Exception {
		try {
			userVO vo = new userVO();
			vo.setPk(user_pk);
			
			postVO pvo = new postVO();
			pvo.setUser_pk(user_pk);
			
			subVO svo = new subVO();
			svo.setP_user_pk(user_pk);
			
			ModelAndView mv = new ModelAndView();
			mv.setViewName("getUserPage");
			mv.addObject("data", userService.getUserDetail(vo, pvo, svo));
			return mv;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 이름으로 유저 정보를 검색한다.
	 * @param name
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{name}", method = RequestMethod.GET)
	public ModelAndView nameGetUser(@PathVariable("name") String name) throws Exception {
		try {
			userVO vo = new userVO();
			vo.setName(name);
			
			ModelAndView mv = new ModelAndView();
			mv.setViewName("nameUser");
			mv.addObject("userInfo", userService.nameGetUser(vo));
			return mv;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
		
}
