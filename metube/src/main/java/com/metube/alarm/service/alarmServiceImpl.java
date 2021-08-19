package com.metube.alarm.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.metube.alarm.dao.alarmDAO;
import com.metube.alarm.vo.alarmVO;

import org.springframework.stereotype.Service;

@Service("AlarmService")
public class alarmServiceImpl implements alarmService{

	@Resource(name = "AlarmDAO")
	private alarmDAO alarmDAO;

	@Override
	public int save(alarmVO vo) throws Exception {
		return alarmDAO.save(vo);
	}

	@Override
	public List<alarmVO> getList(HttpSession session) throws Exception {
		alarmVO vo = new alarmVO();
		vo.setP_user_pk((int)session.getAttribute("user_pk"));
		return alarmDAO.getList(vo);
	}

	@Override
	public int delete(String alarm_pk) throws Exception {
		alarmVO vo = new alarmVO();
		vo.setPk(Integer.parseInt(alarm_pk));
		return alarmDAO.delete(vo);
	}
}
