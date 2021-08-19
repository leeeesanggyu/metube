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
		<c:forEach var="communityList" items="${data.communityList}">
			<div class="oneContent">
				<c:if test="${communityList.is_delete eq '0'}">
					<a href="/post/${communityList.pk}/normal/0">
						<p>
							<img src="/upload/image/${communityList.img_name}/${communityList.img_ext}" />
						</p>
						<p>${communityList.title}</p>
						<div class="small">
							<p>${communityList.name }</p>
							<c:if test="${communityList.update_at eq null}">
								<p>조회수 ${communityList.view_count }회 • ${communityList.create_at }</p>
							</c:if>
							<c:if test="${communityList.update_at ne null}">
								<p>조회수 ${communityList.view_count }회 • 수정 ${communityList.update_at }</p>
							</c:if>
						</div>
					</a>
				</c:if>

				<c:if test="${communityList.is_delete eq '1'}">
					<p>======================</p>
					<p>[ 관리자에 의해 삭제된 게시글 ]</p>
					<p>======================</p>
				</c:if>
			</div>
		</c:forEach>
	</div>
</body>
</html>