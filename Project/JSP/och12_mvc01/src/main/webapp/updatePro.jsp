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
	<jsp:useBean id="member" class="och12_mvc01.Member"></jsp:useBean>
	<jsp:setProperty property="*" name="member" />
	<%
	int result = MemberDao.getInstance().update(member);
	if (result > 0) {
		%>
		<script type="text/javascript">
		alert("사용자 정보 수정 완료");
		location.href="main.jsp";
		</script>
		<%
	}
	else{
		%>
		<script type="text/javascript">
		alert("사용자 정보 수정 오류!");
		history.back();
		</script>
		<%
	}
	%>
</body>
</html>