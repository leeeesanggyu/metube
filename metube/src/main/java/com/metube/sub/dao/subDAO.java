package com.metube.sub.dao;

import com.metube.sub.vo.subVO;

public interface subDAO {

	int sub_add(subVO vo) throws Exception;

	subVO getSub(subVO vo)  throws Exception;

}
