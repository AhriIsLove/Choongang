<%@page import="och12_mvc01.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="error.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	String id = request.getParameter("id");
	String passwd = request.getParameter("passwd");

	MemberDao memberDao = MemberDao.getInstance();

	int result = memberDao.check(id, passwd);

	//성공 : 1
	if (result == 1) {
		session.setAttribute("id", id);
		System.out.println("Login Success...");
		response.sendRedirect("main.jsp");
	}
	//PW 틀림 : 0
	else if (result == 0) {
		session.setAttribute("id", "");
	%>
	<script type="text/javascript">
		alert("암호 틀림!!!");
		history.go(-1);
	</script>
	<%
	}
	//ID 없음 : -1
	else {
	%>
	<script type="text/javascript">
		alert("아이디 없음!!!");
		history.go(-1);
	</script>
	<%
	}
	%>

</body>
</html>