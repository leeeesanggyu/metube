package com.metube.sub.dao;

import org.springframework.stereotype.Repository;

import com.metube.common.dao.mysqlAbstractMapper;
import com.metube.sub.vo.subVO;

@Repository("SubDAO")
public class subDAOImpl extends mysqlAbstractMapper implements subDAO {

	@Override
	public int sub_add(subVO vo) throws Exception {
		return insert("SubDAO.sub_add", vo);
	}

	@Override
	public subVO getSub(subVO vo) throws Exception {
		return selectOne("SubDAO.getSub", vo);
	}

}
