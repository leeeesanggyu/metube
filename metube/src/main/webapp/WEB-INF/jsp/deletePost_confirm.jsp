<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%
	int post_user_pk = (int)request.getAttribute("user_pk");
	int user_pk = (int)session.getAttribute("user_pk");
	int post_pk = (int)request.getAttribute("post_pk");
	int role = (int)request.getAttribute("role");

%>
<%
if(post_user_pk != user_pk) {
%>
	<script>
	alert("자신이 작성한 글만 삭제할 수 있습니다 ! ");
	history.back();
	</script>
<%
}
%>
<script>
function delete_confirm(){
      answer = confirm("정말 삭제하시겠습니까?");
      if (answer){
      	  location.href = 'deletePost.do?pk=<%=post_pk%>';
      }
      else{
    	  history.back();
      }
  }
</script>
</head>
<body onload="delete_confirm()">
</body>
</html>