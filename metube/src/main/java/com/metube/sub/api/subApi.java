package com.metube.sub.api;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.metube.sub.service.subService;
import com.metube.sub.vo.subVO;

@Controller
@RequestMapping(value="/sub")
public class subApi {
	
	@Resource(name = "SubService")
	private subService subService;
	
	/**
	 * 구독 한다.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/", method=RequestMethod.POST)
	public boolean sub_add(@RequestBody subVO vo) throws Exception {
		try {
			subService.sub_add(vo);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 구독을 삭제한다.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/", method=RequestMethod.DELETE)
	public boolean Sub_Delete(@RequestBody subVO vo) throws Exception {
		try {
			subService.sub_delete(vo);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
