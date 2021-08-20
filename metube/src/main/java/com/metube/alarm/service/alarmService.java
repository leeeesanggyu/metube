package com.metube.alarm.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.metube.alarm.vo.alarmVO;

public interface alarmService {

	int save(alarmVO vo) throws Exception;

	List<alarmVO> getList(HttpSession session)throws Exception;

	int delete(String alarm_pk) throws Exception;

	int allDelete(HttpSession session) throws Exception;

}
