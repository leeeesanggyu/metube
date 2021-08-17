package com.metube.common.socket;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class socketHandler extends TextWebSocketHandler {

	//로그인 한 전체
	List<WebSocketSession> sessions = new ArrayList<WebSocketSession>();
	
	// 1대1
	Map<String, WebSocketSession> userSessionsMap = new HashMap<String, WebSocketSession>();

	// 로그 메시지
	private void log(String logmsg) {
		System.out.println(new Date() + " : " + logmsg);
	}
	
	//웹소켓 email 가져오기
	private String getEmail(WebSocketSession session) {
		Map<String, Object> httpSession = session.getAttributes();
		String email = (String) httpSession.get("email");
		
		if(email == null) {
			return session.getId();
		} else {
			return email;
		}
	}
	
	//서버에 접속이 성공 했을때
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessions.add(session);

		String senderEmail = getEmail(session);
		log("접속 : " + senderEmail);
		
		userSessionsMap.put(senderEmail , session);
	}

	//소켓에 메세지를 보냈을때
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println("응답 :" + message);

		//protocol : cmd , 댓글작성자_pk, 작성자 pk, 작성자email, 제목, post_pk
		String msg = message.getPayload();
		
		if(StringUtils.isNotEmpty(msg)) {
			String[] strs = msg.split(",");
			
			if(strs != null && strs.length == 7) {
				String cmd = strs[0];
				String s_user_pk = strs[1]; 
				String user_name = strs[2];
				String p_user_pk = strs[3];
				String user_email = strs[4];
				String content = strs[5];
				String post_pk = strs[6];

				//작성자가 로그인 해서 있다면
				WebSocketSession boardWriterSession = userSessionsMap.get(user_email);

				if("comment".equals(cmd) && boardWriterSession != null) {
					TextMessage tmpMsg = new TextMessage(user_name + "님이 동영상 \"" + content + "\"에 댓글을 남겼습니다." + "post_pk" + post_pk);
					boardWriterSession.sendMessage(tmpMsg);

				}else if("like".equals(cmd) && boardWriterSession != null) {
					TextMessage tmpMsg = new TextMessage(user_name + "님이 " + content +
							"님을 팔로우를 시작했습니다.");
					boardWriterSession.sendMessage(tmpMsg);

				}else if("sub".equals(cmd) && boardWriterSession != null) {
					TextMessage tmpMsg = new TextMessage(user_name + "님이 ");
					boardWriterSession.sendMessage(tmpMsg);
				}
			}
			
		}
	}

	//연결 해제될때
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		//System.out.println("afterConnectionClosed " + session + ", " + status);
		userSessionsMap.remove(session.getId());
		sessions.remove(session);
	}

	
}