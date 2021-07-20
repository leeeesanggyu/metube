package com.metube.comment.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.metube.comment.service.commentService;
import com.metube.comment.vo.commentVO;

@Controller
@RequestMapping(value="/comment")
public class commentController {

	@Resource(name = "CommentService")
	private commentService commentService;
	
	/**
	 * 댓글을 작성한다.
	 * @param vo : post_pk, content
	 * @param session : user_pk
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/", method = RequestMethod.POST)
	public ModelAndView createComment(@ModelAttribute commentVO vo, HttpSession session) throws Exception {
		System.out.println("commentController - createComment");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("getPost");
		mv.addObject("comment", commentService.createComment(vo, session));
		return mv;
	}
	
	/**
	 * 댓글을 삭제한다.
	 * @param pk
	 * @param user_pk
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/", method = RequestMethod.DELETE)
	public ModelAndView deleteComment(int pk, HttpSession session) throws Exception {
		System.out.println("commentController - deleteComment");
		commentVO vo = new commentVO();
		vo.setPk(pk);
		vo.setUser_pk((int)session.getAttribute("user_pk"));
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("getPost");
		mv.addObject("commentDelete", commentService.deleteComment(vo));
		return mv;
	}
}
