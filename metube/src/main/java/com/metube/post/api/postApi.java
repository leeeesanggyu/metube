package com.metube.post.api;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
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

@Controller
@RequestMapping(value="/post")
public class postApi {
	
	@Resource(name="uploadPath")
    String uploadPath;
	
	@Resource(name = "PostService")
	private postService postService;
	
	@Resource(name = "UploadService")
	private uploadService uploadService;
	
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
			postVO pvo = new postVO();
			pvo.setTitle(request.getParameter("title"));
			pvo.setDescription(request.getParameter("description"));
			pvo.setKind(Integer.parseInt(request.getParameter("kind")));
			pvo.setUser_pk(Integer.parseInt(request.getParameter("user_pk")));
			int create_result = postService.createPost(pvo);
			
	        UUID uuid = UUID.randomUUID();

	        //video
			String video_fileName = (request.getFile("video").getOriginalFilename());
			String video_ext = video_fileName.substring(video_fileName.lastIndexOf(".") + 1);
	        String video_savedName = uuid.toString() + "_" + video_fileName;
	        File video_target = new File(uploadPath, video_savedName);
			//확장자 없는 파일명
	        String video_noExtName = FilenameUtils.getBaseName(video_savedName);
	        
	        //image
	        String image_fileName = (request.getFile("image").getOriginalFilename());
	    	//확장자 구하기
	    	String image_ext = image_fileName.substring(image_fileName.lastIndexOf(".") + 1);
	        String image_savedName =  uuid.toString() + "_" + image_fileName;
	        File image_target = new File(uploadPath, image_savedName);
			//확장자 없는 파일명
	        String image_noExtName = FilenameUtils.getBaseName(image_savedName);
	        
	        //경로 생성
	        if (!new File(uploadPath).exists()) {
	            new File(uploadPath).mkdirs();
	        }
	        //파일 복사
	        try {
	            FileCopyUtils.copy(request.getFile("image").getBytes(), image_target);
	            FileCopyUtils.copy(request.getFile("video").getBytes(), video_target);
	        } catch(Exception e) {
	            e.printStackTrace();
	        }
			
	        //썸네일 생성
	        File thumbFile = makeThumbnail(image_savedName, image_ext);
	        
	        uploadVO uvo = new uploadVO();
			uvo.setPost_pk(create_result);
			uvo.setImg_name("THUMB_" + image_noExtName);
			uvo.setImg_ext(image_ext);
			uvo.setVideo_name(video_noExtName);
			uvo.setVideo_ext(video_ext);

			uploadService.saveDataURL(uvo);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 썸네일을 생성합니다. * 250 x 150 크기의 썸네일을 만듭니다.
	 * @param image_target
	 * @param image_noExtName
	 * @param image_ext
	 * @throws IOException 
	 * return thumbpath
	 */
	private File makeThumbnail(String image_savedName, String image_ext) throws IOException {
		// 저장된 원본파일로부터 BufferedImage 객체를 생성합니다. 
		BufferedImage srcImg = ImageIO.read(new File(uploadPath + "\\" + image_savedName)); 
	
		// 썸네일의 너비와 높이 입니다. 
		int dw = 250, dh = 150; 
		
		// 원본 이미지의 너비와 높이 입니다. 
		int ow = srcImg.getWidth(); 
		int oh = srcImg.getHeight(); 
		
		// 원본 너비를 기준으로 하여 썸네일의 비율로 높이를 계산합니다. 
		int nw = ow; 
		int nh = (ow * dh) / dw; 
		
		// 계산된 높이가 원본보다 높다면 crop이 안되므로 
		// 원본 높이를 기준으로 썸네일의 비율로 너비를 계산합니다. 
		if(nh > oh) { nw = (oh * dw) / dh; nh = oh; } 
		
		// 계산된 크기로 원본이미지를 가운데에서 crop 합니다. 
		BufferedImage cropImg = Scalr.crop(srcImg, (ow-nw)/2, (oh-nh)/2, nw, nh); 
		
		// crop된 이미지로 썸네일을 생성합니다. 
		BufferedImage destImg = Scalr.resize(cropImg, dw, dh); 
		
		// 썸네일을 저장합니다. 이미지 이름 앞에 "THUMB_" 를 붙여 표시했습니다. 
		String thumbName = uploadPath + "/THUMB_" + image_savedName; 
		File thumbFile = new File(thumbName);
		ImageIO.write(destImg, image_ext.toUpperCase(), thumbFile);
		return thumbFile;
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
		postVO vo = new postVO();
		vo.setPk(post_pk);
		try {		
			postService.is_deletePost(vo);
			return true;
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
	@RequestMapping(value="/modify", method = RequestMethod.PUT)
	public boolean ModifyPost(@RequestBody postVO vo) throws Exception {
		try {		
			//현재 시간 구하기
	        ZoneId zid = ZoneId.systemDefault();
			ZonedDateTime datetime = ZonedDateTime.now(zid);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "uuuu-MM-dd HH:mm:ss" );
	        String timeStamp = datetime.format(formatter);
	        vo.setUpdate_at(timeStamp);
	    
			postService.modifyPost(vo);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/notice", method = RequestMethod.POST)
	public boolean CreateNoticePost(@RequestBody postVO vo) throws Exception {
		try {		
			postService.createNotice(vo);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
