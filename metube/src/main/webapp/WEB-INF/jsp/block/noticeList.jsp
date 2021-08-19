<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="content">
		<c:forEach var="noticeList" items="${data.noticeList}">
			<div class="oneContent">
				<a href="/post/${noticeList.pk}/notice/0">
					<p>* NOTICE *</p>
					<p>${noticeList.title}</p>
					<div class="small">
						<p>${noticeList.name }</p>
						<c:if test="${noticeList.update_at eq null}">
							<p>조회수 ${noticeList.view_count }회 • ${noticeList.create_at }</p>
						</c:if>
						<c:if test="${noticeList.update_at ne null}">
							<p>조회수 ${noticeList.view_count }회 • 수정 ${noticeList.update_at }</p>
						</c:if>
					</div>
				</a>
			</div>
		</c:forEach>
	</div>
</body>
</html>