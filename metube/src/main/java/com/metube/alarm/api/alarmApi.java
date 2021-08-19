package com.metube.alarm.api;

import javax.annotation.Resource;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.metube.alarm.service.alarmService;
import com.metube.alarm.vo.alarmVO;

@Controller
@RequestMapping(value="/alarm")
public class alarmApi {

	@Resource(name = "AlarmService")
	private alarmService alarmService;
	
	/**
	 * 알림 리스트를 가져온다.
	 * @param vo: p_user_pk
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/list/{p_user_pk}", method=RequestMethod.GET)
	public List<alarmVO> AlarmList(
		@PathVariable("p_user_pk") int p_user_pk	
	) throws Exception {
		try {
			alarmVO vo = new alarmVO();
			vo.setP_user_pk(p_user_pk);
			return alarmService.getList(vo);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
