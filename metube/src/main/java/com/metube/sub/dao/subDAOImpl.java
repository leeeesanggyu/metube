package com.metube.sub.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.metube.common.dao.mysqlAbstractMapper;
import com.metube.post.vo.postVO;
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

	@Override
	public int sub_count(subVO vo) throws Exception {
		return selectOne("SubDAO.sub_count", vo);
	}

	@Override
	public int sub_delete(subVO vo) throws Exception {
		return delete("SubDAO.delete_Sub", vo);
	}

	@Override
	public List<postVO> getSubPost(subVO vo) throws Exception {
		return selectList("SubDAO.getSubPost", vo);
	}

}
