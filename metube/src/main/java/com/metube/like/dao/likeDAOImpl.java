package com.metube.like.dao;

import org.springframework.stereotype.Repository;

import com.metube.common.dao.mysqlAbstractMapper;
import com.metube.like.vo.likeVO;
import com.metube.sub.vo.subVO;

@Repository("likeDAO")
public class likeDAOImpl extends mysqlAbstractMapper implements likeDAO{

	@Override
	public int post_like(likeVO vo) throws Exception {
		return update("likeDAO.post_like_add", vo);
	}

	@Override
	public int post_like_count(likeVO lvo) throws Exception {
		return selectOne("likeDAO.post_like_count", lvo);
	}

	@Override
	public int like_delete(likeVO vo) throws Exception {
		return delete("likeDAO.post_like_delete", vo);
	}

	@Override
	public likeVO is_like(likeVO vo) throws Exception {
		return selectOne("likeDAO.is_like", vo);
	}

}
