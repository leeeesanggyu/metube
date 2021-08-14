package com.metube.sub.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.metube.post.vo.postVO;
import com.metube.sub.vo.subVO;

public interface subService {

	int sub_add(subVO vo) throws Exception;

	subVO getSub(int user_pk, HttpSession session) throws Exception;

	int sub_delete(subVO vo) throws Exception;

	List<postVO> getSubPostList(HttpSession session) throws Exception;
}
