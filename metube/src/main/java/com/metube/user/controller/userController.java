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
	
	@Resource(name = "PostService")
	private postService postService;
	
	@Resource(name = "SubService")
	private subService subService;
	
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
		try {
			userService.logout(session);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RequestMapping(value="/detail/{user_pk}", method = RequestMethod.GET)
	public ModelAndView getUserpage(
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
			mv.addObject("userInfo", userService.getUser(vo));
			mv.addObject("postList", postService.getUserPostList(pvo));
			mv.addObject("communityList", postService.getUserCommunityList(pvo));
			mv.addObject("sub_count", subService.sub_count(svo));

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
			
			List<userVO> result = userService.nameGetUser(vo);
			if(result == null) {
				mv.addObject("userInfo");
			}else {
				mv.addObject("userInfo", result);
			}
			return mv;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
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
			String hash_Password = BCrypt.hashpw(vo.getPassword(), BCrypt.gensalt());
			vo.setPassword(hash_Password);
			
			userService.signUp(vo);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 유저에게 잠금을 건다.
	 * @param vo: pk, name, lock
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/lock", method = RequestMethod.POST)
	public boolean userLock(@RequestBody userVO vo) throws Exception {
		try {
			userService.userLock(vo);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
	
	@ResponseBody
	@RequestMapping(value="/withdrawal/{user_pk}", method = RequestMethod.DELETE)
	public boolean withdrawal(
			@PathVariable("user_pk") int user_pk, HttpSession session
	) throws Exception {
		try {
			userVO vo = new userVO();
			vo.setPk(user_pk);
			userService.withdrawal(vo);
			userService.logout(session);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
