package com.metube.upload.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import com.metube.upload.vo.uploadVO;

public interface uploadService {

	int saveDataURL(uploadVO uvo) throws Exception;

	void dataSave(byte[] bytes, File target) throws IOException;

	File makeThumbnail(String image_savedName, String image_ext) throws IOException;

	HashMap<String,String> getDataName(String originalFilename);

}
