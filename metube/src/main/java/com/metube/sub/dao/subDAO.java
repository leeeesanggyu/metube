package com.metube.sub.dao;

import java.util.List;

import com.metube.post.vo.postVO;
import com.metube.sub.vo.subVO;

public interface subDAO {

	int sub_add(subVO vo) throws Exception;

	subVO getSub(subVO vo) throws Exception;

	int sub_count(subVO vo) throws Exception;

	int sub_delete(subVO vo) throws Exception;

	List<postVO> getSubPost(subVO vo) throws Exception;

}
