package com.metube.upload.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.metube.upload.dao.uploadDAO;
import com.metube.upload.vo.uploadVO;

@Service("UploadService")
public class uploadServiceImpl implements uploadService{

	@Resource(name = "UploadDAO")
	private uploadDAO uploadDAO;
	
	@Resource(name="uploadPath")
    String uploadPath;
	
	@Override
	public int saveDataURL(uploadVO uvo) throws Exception {
		return uploadDAO.saveDataURL(uvo);
	}

	@Override
	public void dataSave(byte[] bytes, File target) throws IOException {
		FileCopyUtils.copy(bytes, target);
	}

	/**
	 * 썸네일을 생성합니다. * 250 x 150 크기의 썸네일을 만듭니다.
	 * @param image_target
	 * @param image_noExtName
	 * @param image_ext
	 * @return 
	 * @throws IOException 
	 * return thumbpath
	 */
	@Override
	public File makeThumbnail(String image_savedName, String image_ext) throws IOException {
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
	 * 파일 uuid + Name, 순수 파일명 , ext 리턴 
	 */
	@Override
	public HashMap<String,String> getDataName(String originalFilename) {
        UUID uuid = UUID.randomUUID();

    	HashMap<String,String> dataName = new HashMap<String,String>();
    	
		String uuid_name =  uuid.toString() + "_" + originalFilename;
        String data_noExt = FilenameUtils.getBaseName(uuid_name);
    	String data_ext = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

    	dataName.put("uuid_name", uuid_name);
    	dataName.put("data_noExt", data_noExt);
    	dataName.put("data_ext", data_ext);
    	return dataName;
	}

}
