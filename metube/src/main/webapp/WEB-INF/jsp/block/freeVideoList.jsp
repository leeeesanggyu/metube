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
		<c:forEach var="postList" items="${data.postList}">
			<div class="oneContent">
				<c:if test="${postList.is_delete eq '0'}">
					<a href="/post/${postList.pk}/normal">
						<p>
							<img src="/upload/image/${postList.img_name}/${postList.img_ext}" />
						</p>
						<p>${postList.title}</p>
						<div class="small">
							<a href="/user/detail/${postList.user_pk }"><p>${postList.name }</p></a>
							<c:if test="${postList.update_at eq null}">
								<p>조회수 ${postList.view_count }회 • ${postList.create_at }</p>
							</c:if>
							<c:if test="${postList.update_at ne null}">
								<p>조회수 ${postList.view_count }회 • 수정 ${postList.update_at }</p>
							</c:if>
						</div>
					</a>
				</c:if>

				<c:if test="${postList.is_delete eq '1'}"><br><br><br><br><br><br>
					<p>======================</p>
					<p>&nbsp;[ 관리자에 의해 삭제된 게시글 ]</p>
					<p>======================</p><br><br><br><br><br><br>
				</c:if>
			</div>
		</c:forEach>
	</div>

</body>
</html>