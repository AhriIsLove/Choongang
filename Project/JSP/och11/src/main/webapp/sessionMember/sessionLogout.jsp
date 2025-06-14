<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	String id = session.getAttribute("id").toString();
	System.out.println("Logout session id : " + id);
	//세션 종료
	session.invalidate();
	%>
	<script type="text/javascript">
		alert("로그아웃 되었습니다.");
		location.href = "sessionMain.jsp";
	</script>
</body>
</html>