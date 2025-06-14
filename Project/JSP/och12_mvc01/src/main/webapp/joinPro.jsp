<%@page import="och12_mvc01.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 
		Bean 설정
		Bean에 모든 parameter Setting 
	-->
	<jsp:useBean id="member" class="och12_mvc01.Member"></jsp:useBean>
	<jsp:setProperty property="*" name="member" />
	<%
	MemberDao md = MemberDao.getInstance();
	int result = md.insert(member);

	if (result > 0) {
	%>
	<script type="text/javascript">
		alert("회원가입 축하!!");
		location.href = "loginForm.jsp";
	</script>
	<%
	} else {
	%>
	<script type="text/javascript">
		alert("회원가입 실패");
		location.href = "joinForm.jsp";
	</script>
	<%
	}
	%>
</body>
</html>