package com.metube.comment.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.metube.comment.vo.commentVO;
import com.metube.common.dao.mysqlAbstractMapper;
import com.metube.post.vo.postVO;

@Repository("CommentDAO")
public class commentDAOImpl extends mysqlAbstractMapper implements commentDAO {

	@Override
	public int createComment(commentVO vo) throws Exception {
		System.out.println("commentDAO - createComment");
		return insert("CommentDAO.createComment", vo);
	}

	@Override
	public List<commentVO> getComment(commentVO vo) throws Exception {
		return selectList("CommentDAO.getComment", vo);
	}

}
