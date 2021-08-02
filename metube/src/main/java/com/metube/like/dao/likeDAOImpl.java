package com.metube.like.dao;

import org.springframework.stereotype.Repository;

import com.metube.common.dao.mysqlAbstractMapper;
import com.metube.like.vo.likeVO;

@Repository("likeDAO")
public class likeDAOImpl extends mysqlAbstractMapper implements likeDAO{

	@Override
	public int post_like(likeVO vo) throws Exception {
		return update("likeDAO.post_like", vo);
	}

	@Override
	public int post_like_count(likeVO lvo) throws Exception {
		return selectOne("likeDAO.post_like_count", lvo);
	}

}
