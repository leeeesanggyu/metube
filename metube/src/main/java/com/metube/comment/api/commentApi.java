package com.metube.comment.api;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.metube.comment.service.commentService;
import com.metube.comment.vo.commentVO;

@Controller
@RequestMapping(value="/comment")
public class commentApi {

	@Resource(name = "CommentService")
	private commentService commentService;
	
	/**
	 * 댓글을 작성한다.
	 * @param vo : post_pk, content
	 * @param session : user_pk
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/", method = RequestMethod.POST)
	public boolean createComment(
			@RequestBody commentVO vo, HttpSession session
	) throws Exception {
		try {
			commentService.createComment(vo, session);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 댓글을 삭제한다.
	 * @param pk
	 * @param user_pk
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/{comment_pk}", method = RequestMethod.DELETE)
	public boolean deleteComment(@PathVariable("comment_pk") int comment_pk) throws Exception {
		try {
			commentVO vo = new commentVO();
			vo.setPk(comment_pk);
			
			commentService.deleteComment(vo);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
