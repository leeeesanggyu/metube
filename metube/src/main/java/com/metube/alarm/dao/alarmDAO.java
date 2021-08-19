package com.metube.alarm.dao;

import java.util.List;

import com.metube.alarm.vo.alarmVO;

public interface alarmDAO {

	int save(alarmVO vo) throws Exception;

	List<alarmVO> getList(alarmVO vo) throws Exception;
}
