package com.metube.sub.service;

import javax.servlet.http.HttpSession;

import com.metube.sub.vo.subVO;

public interface subService {

	int sub_add(subVO vo) throws Exception;

	subVO getSub(int user_pk, HttpSession session) throws Exception;
}
