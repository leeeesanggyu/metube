package com.metube.alarm.service;

import java.util.List;

import com.metube.alarm.vo.alarmVO;

public interface alarmService {

	int save(alarmVO vo) throws Exception;

	List<alarmVO> getList(alarmVO vo) throws Exception;

	int delete(String alarm_pk) throws Exception;

}
