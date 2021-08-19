package com.metube.alarm.api;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

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
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public List<alarmVO> AlarmList(
			HttpSession session	
	) throws Exception {
		try {
			return alarmService.getList(session);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
