package com.metube.upload.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.metube.upload.dao.uploadDAO;
import com.metube.upload.vo.uploadVO;

@Service("UploadService")
public class uploadServiceImpl implements uploadService{

	@Resource(name = "UploadDAO")
	private uploadDAO uploadDAO;
	
	@Override
	public int saveDataURL(uploadVO uvo) throws Exception {
		return uploadDAO.saveDataURL(uvo);
	}

}
