package com.metube.sub.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.metube.post.vo.postVO;
import com.metube.sub.service.subService;
import com.metube.sub.vo.subVO;

@Controller
@RequestMapping(value="/sub")
public class subController {

	@Resource(name = "SubService")
	private subService subService;
	
	@ResponseBody
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public boolean sub_add(@RequestBody subVO vo) throws Exception {
		try {
			System.out.println(vo.getC_user_pk());
			System.out.println(vo.getP_user_pk());

			subService.sub_add(vo);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}

