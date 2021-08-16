package com.metube.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.captcha.Captcha;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.metube.common.captcha.CaptchaUtil;

@Controller 
public class captchaController { 
	
	// 페이지 매핑
	@GetMapping("/captcha") 
	public String Captcha() { 
		return "captcha"; 
	} 
	
	// captcha 이미지 가져오는 메서드 
	@GetMapping("/captchaImg") 
	@ResponseBody 
	public void captchaImg(HttpServletRequest req, HttpServletResponse res) throws Exception{ 
		new CaptchaUtil().getImgCaptCha(req, res); 
	} 
	
	// 전달받은 문자열로 음성 가져오는 메서드 
	@GetMapping("/captchaAudio") 
	@ResponseBody 
	public void captchaAudio(HttpServletRequest req, HttpServletResponse res) throws Exception{
		Captcha captcha = (Captcha) req.getSession().getAttribute(Captcha.NAME); 
		String getAnswer = captcha.getAnswer(); 
		new CaptchaUtil().getAudioCaptCha(req, res, getAnswer); 
	}
	
	// 사용자가 입력한 보안문자 체크하는 메서드 
	@PostMapping("/chkAnswer") 
	@ResponseBody 
	public void chkAnswer(@RequestBody() String ans, HttpServletRequest req, HttpServletResponse res) { 
		String result = ""; 
		Captcha captcha = (Captcha) req.getSession().getAttribute(Captcha.NAME); 
		
		if(ans!=null && !"".equals(ans)) { 
			if(captcha.isCorrect(ans)) { 
				req.getSession().removeAttribute(Captcha.NAME); 
				res.setStatus(200); 
				}else { 
					res.setStatus(300); 
				} 
		} 
	} 
}