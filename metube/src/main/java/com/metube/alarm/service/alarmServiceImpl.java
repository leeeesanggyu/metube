package com.metube.alarm.service;

import javax.annotation.Resource;

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
}
