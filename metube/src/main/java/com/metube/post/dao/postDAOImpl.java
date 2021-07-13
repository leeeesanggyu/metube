package com.metube.post.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.metube.common.dao.mysqlAbstractMapper;
import com.metube.post.vo.postVO;

@Repository("PostDAO")
public class postDAOImpl extends mysqlAbstractMapper implements postDAO {

	@Override
	public List<postVO> getPostList(postVO vo) throws Exception {
		System.out.println("postDAO - getPostList");
		return selectList("PostDAO.postList", vo);
	}

	@Override
	public int createPost(postVO vo) throws Exception {
		System.out.println("postDAO - createPost");
		return insert("PostDAO.createPost", vo);
	}

	@Override
	public int deletePost(postVO vo) throws Exception {
		System.out.println("postDAO - deletePost");
		return delete("PostDAO.deletePost", vo);
	}

	@Override
	public postVO selectOne(postVO vo) throws Exception {
		return selectOne("PostDAO.selectPost", vo);
	}
}

