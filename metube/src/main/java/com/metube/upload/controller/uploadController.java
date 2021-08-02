package com.metube.upload.controller;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/upload")
public class uploadController {

	@Resource(name="uploadPath")
    String uploadPath;
	
	@ResponseBody
    @RequestMapping(value = "/image/{img_name}/{img_ext}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getImage (
		@PathVariable("img_name") String img_name,
		@PathVariable("img_ext") String img_ext
	) throws Exception {
		InputStream inputStream = new FileInputStream(uploadPath +"/"+ img_name + "." + img_ext);
		return new ResponseEntity<byte[]>(IOUtils.toByteArray(inputStream), HttpStatus.OK);
	}
	
	
}
