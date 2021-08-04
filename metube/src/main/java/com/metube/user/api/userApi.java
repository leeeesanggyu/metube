package com.metube.user.api;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.metube.common.hash.BCrypt;
import com.metube.user.service.userService;
import com.metube.user.vo.userVO;

@Controller
@RequestMapping(value="/user")
public class userApi {

	@Resource(name = "UserService")
	private userService userService;
	
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
	
	/**
	 * 회원가입 한다.
	 * @param vo email, password, name, role
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/", method = RequestMethod.POST)
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
	 * 회원탈퇴 한다.
	 * @param user_pk
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/{user_pk}", method = RequestMethod.DELETE)
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
	
	/**
	 * 유저를 lock 한다.
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
}
