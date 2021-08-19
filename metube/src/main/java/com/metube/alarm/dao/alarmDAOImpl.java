package com.metube.alarm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.metube.alarm.vo.alarmVO;
import com.metube.common.dao.mysqlAbstractMapper;

@Repository("AlarmDAO")
public class alarmDAOImpl extends mysqlAbstractMapper implements alarmDAO{

	@Override
	public int save(alarmVO vo) throws Exception {
		return insert("AlarmDAO.save", vo);
	}

	@Override
	public List<alarmVO> getList(alarmVO vo) throws Exception {
		return selectList("AlarmDAO.getList", vo);
	}

	@Override
	public int delete(alarmVO vo) throws Exception {
		return delete("AlarmDAO.delete", vo);
	}

}
