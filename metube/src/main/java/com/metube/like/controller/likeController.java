package com.metube.like.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.metube.like.service.likeService;
import com.metube.like.vo.likeVO;
import com.metube.sub.vo.subVO;

@Controller
@RequestMapping(value="/like")
public class likeController {

	@Resource(name = "LikeService")
	private likeService likeService;
	
	/**
	 * 게시물 좋아요
	 * @param vo: post_pk, user_pk
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public boolean PostLike(@RequestBody likeVO vo) throws Exception {
		try {
			likeService.post_like(vo);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/del", method=RequestMethod.DELETE)
	public boolean Sub_Delete(@RequestBody likeVO vo) throws Exception {
		try {
			likeService.like_delete(vo);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
