<%@page import="och12_mvc01.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ include file="memberCheck.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	String passwd = request.getParameter("passwd");
	int result = MemberDao.getInstance().delete(id, passwd);//memberCheck.jsp에서 id를 선언했다.
	if(result == 1){
		session.invalidate();
		%>
		<script type="text/javascript">
		alert("탈퇴되었습니다.");
		location.href="main.jsp";
		</script>
		<%
	}
	else if(result == 0){
		%>
		<script type="text/javascript">
		alert("암호가 다릅니다.");
		history.go(-1);
		</script>
		<%
	}
	else{
		%>
		<script type="text/javascript">
		alert("없는 사용자입니다.");
		history.go(-1);
		</script>
		<%
	}
	%>
</body>
</html>