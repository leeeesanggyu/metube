package com.metube.post.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.metube.post.service.postService;
import com.metube.post.vo.postVO;
import com.metube.comment.service.commentService;
import com.metube.comment.vo.commentVO;

@Controller
@RequestMapping(value="/post")
public class postController {

	@Resource(name = "PostService")
	private postService postService;
	
	@Resource(name = "CommentService")
	private commentService commentService;
	
	/**
	 * 게시물 생성 페이지로 간다.
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goCreate.do")
	public ModelAndView goCreatePost() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("createPost");
		return mv;
	}
	
	
	/**
	 * 게시물 목록을 가져온다.
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/list.do")
	public ModelAndView getPostList() throws Exception {
		System.out.println("postController - getPostList");
		postVO vo = new postVO();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main");
		mv.addObject("postList", postService.getPostList(vo));
		return mv;
	}
	
	/**
	 * 게시물을 작성한다
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/post.do", method = RequestMethod.POST)
	public boolean createPost(@RequestBody postVO vo) throws Exception {
		System.out.println("postController - createPost");
		System.out.println("request:" + vo.getTitle());
		try {		
			if(postService.createPost(vo) != 0) {
				return true;
			}
			return false;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 게시물을 삭제한다.
	 * @param session
	 * @param post_pk
	 * @param user_pk
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/post.do", method = RequestMethod.DELETE)
	public ModelAndView deletePost(
			HttpSession session, int post_pk, int user_pk
	) throws Exception {
		System.out.println("postController - deletePost");
		int role = (int)session.getAttribute("role");
		int s_user_pk = (int)session.getAttribute("user_pk");
		
		ModelAndView mv = new ModelAndView();
		if(role > 1) {
			if(user_pk == s_user_pk) {
				postVO vo = new postVO();
				vo.setPk(post_pk);
				
				mv.addObject("deletePost", postService.deletePost(vo));
				mv.setViewName("getPost");
				return mv;
			}
			mv.addObject("result", "mineFail");
			mv.setViewName("getPost");
			return mv;
		}
		mv.addObject("result", "roleFail");
		mv.setViewName("getPost");
		return mv;
	}
	
	/**
	 * 상세 게시물을 불러온다.
	 * @param post_pk
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/detail.do", method = RequestMethod.GET)
	public ModelAndView detailPost(int post_pk) throws Exception {
		System.out.println("postController - detailPost");	
		postVO vo = new postVO();
		vo.setPk(post_pk);
		
		commentVO comment_vo = new commentVO();
		comment_vo.setPost_pk(post_pk);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("post", postService.detailPost(vo));
		mv.addObject("comment", commentService.getComment(comment_vo));
		mv.setViewName("detailPost");
		return mv;
	}
	
	
}
