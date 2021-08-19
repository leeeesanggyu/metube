package com.metube.post.api;

import java.io.File;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.metube.post.service.postService;
import com.metube.post.vo.postVO;
import com.metube.upload.service.uploadService;
import com.metube.upload.vo.uploadVO;
import com.metube.common.service.commonService;


@Controller
@RequestMapping(value="/post")
public class postApi {
	
	@Resource(name="uploadPath")
    String uploadPath;
	
	@Resource(name = "PostService")
	private postService postService;
	
	@Resource(name = "UploadService")
	private uploadService uploadService;
	
	@Resource(name = "CommonService")
	private commonService commonService;
	
	/**
	 * 게시물을 작성한다
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/", method = RequestMethod.POST)
	public boolean CreatePost(
			MultipartHttpServletRequest request
	) throws Exception {
		try {		
			
	        //video
			HashMap<String,String> video_name = 
	        		uploadService.getDataName(request.getFile("video").getOriginalFilename());
			File video_target = new File(uploadPath, video_name.get("uuid_name"));

	        //image
	        HashMap<String,String> image_name = 
	        		uploadService.getDataName(request.getFile("image").getOriginalFilename());
	        File image_target = new File(uploadPath, image_name.get("uuid_name"));

	        //경로 생성
	        if (!new File(uploadPath).exists()) {
	            new File(uploadPath).mkdirs();
	        }
	        
	        uploadService.dataSave(request.getFile("video").getBytes(), video_target);
	        uploadService.dataSave(request.getFile("image").getBytes(), image_target);
	        uploadService.makeThumbnail(image_name.get("uuid_name"), image_name.get("data_ext"));
	        
	        postVO pvo = new postVO();
			pvo.setTitle(request.getParameter("title"));
			pvo.setDescription(request.getParameter("description"));
			pvo.setKind(Integer.parseInt(request.getParameter("kind")));
			pvo.setUser_pk(Integer.parseInt(request.getParameter("user_pk")));
        	int create_result = postService.createPost(pvo);
	        
	        uploadVO uvo = new uploadVO();
			uvo.setPost_pk(create_result);
			uvo.setImg_name("THUMB_" + image_name.get("data_noExt"));
			uvo.setImg_ext(image_name.get("data_ext"));
			uvo.setVideo_name(video_name.get("data_noExt"));
			uvo.setVideo_ext(video_name.get("data_ext"));
			uploadService.saveDataURL(uvo);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 게시물을 삭제한다.
	 * @param post_pk
	 * @param user_pk
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/{post_pk}", method = RequestMethod.DELETE)
	public boolean DeletePost(
		@PathVariable("post_pk") int post_pk
	) throws Exception {
		postVO vo = new postVO();
		vo.setPk(post_pk);
		try {		
			postService.deletePost(vo);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 게시물을 수정한다
	 * @param vo: pk 수정할 게시물,
	 * 			수정데이터: title, description
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/", method = RequestMethod.PUT)
	public boolean ModifyPost(
			@RequestBody postVO vo, HttpServletRequest request
	) throws Exception {
		try {		
			System.out.println("3 : " + request.getMethod());

	        vo.setUpdate_at(commonService.getTime());
			postService.modifyPost(vo);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 공지사항 작성한다.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/notice", method = RequestMethod.POST)
	public boolean CreateNoticePost(
			@RequestBody postVO vo, HttpServletRequest request
	) throws Exception {
		try {		
			System.out.println("3 : " + request.getMethod());
			
			postService.createNotice(vo);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 운영자 게시물 삭제 처리
	 * @param post_pk
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/admin/{post_pk}", method = RequestMethod.DELETE)
	public boolean Is_deletePost(
			@PathVariable("post_pk") int post_pk
	) throws Exception {
		try {
			postVO vo = new postVO();
			vo.setPk(post_pk);
			postService.is_deletePost(vo);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
