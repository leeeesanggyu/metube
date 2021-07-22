package com.metube.post.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	@RequestMapping(value="/goCreate")
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
	@RequestMapping(value="/list")
	public ModelAndView getPostList() throws Exception {
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
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public boolean createPost(@RequestBody postVO vo) throws Exception {
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
	@ResponseBody
	@RequestMapping(value="/{post_pk}", method = RequestMethod.DELETE)
	public boolean deletePost(
		@PathVariable("post_pk") int post_pk
	) throws Exception {
		postVO vo = new postVO();
		vo.setPk(post_pk);
		try {		
			if(postService.deletePost(vo) != 0) {
				return true;
			}
			return false;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
			
	}
	
	/**
	 * 상세 게시물을 불러온다.
	 * @param post_pk
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/detail/{post_pk}", method = RequestMethod.GET)
	public ModelAndView detailPost(@PathVariable("post_pk") int post_pk) throws Exception {
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
