package com.metube.common.service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service("CommonService")
public class commonServiceImpl implements commonService{

	@Transactional(isolation = Isolation.READ_COMMITTED)
	//현재 시간 구하기
	@Override
	public String getTime() throws Exception {
		ZoneId zid = ZoneId.systemDefault();
		ZonedDateTime datetime = ZonedDateTime.now(zid);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "uuuu-MM-dd HH:mm:ss" );
        return datetime.format(formatter);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public Cookie view_count_check(Cookie[] cookies, int post_pk) throws Exception {
	    Map<String, String> map = new HashMap<String, String>();
	    if(cookies != null){
		    for (int i = 0; i < cookies.length; i++) {
			    Cookie obj = cookies[i];
			    map.put(obj.getName(),obj.getValue());
		    }
	    }
	    // 저장된 쿠키중에 read_count 만 불러오기
	    String readCount = (String) map.get("read_count");

	     // 저장될 새로운 쿠키값 생성
	    String newReadCount = "|" + post_pk;

	    // 저장된 쿠키에 새로운 쿠키값이 존재하는 지 검사
	    if ( StringUtils.indexOfIgnoreCase(readCount, newReadCount) == -1 ) {
	          // 없을 경우 쿠키 생성
	          Cookie cookie = new Cookie("read_count", readCount + newReadCount);
	          return cookie;
	    }
	    return null;
	}

}
