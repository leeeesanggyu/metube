package com.metube.common.service;

import javax.servlet.http.Cookie;

public interface commonService {

	String getTime() throws Exception;

	Cookie view_count_check(Cookie[] cookies, int post_pk) throws Exception;

}
