<%@page import="och12_mvc01.Member"%>
<%@page import="java.util.List"%>
<%@page import="och12_mvc01.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="memberCheck.jsp"%><!-- main.jsp에 들어올떄마다 memberCheck.jsp 실행 : Filter와 같은 용도 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="yellow">
	<%
	List<Member> list = MemberDao.getInstance().list();
	if (list == null || list.size() == 0) {
		out.println("회원이 없습니다.");
	} else {
	%>
	<h1>회원정보</h1>
	<table bgcolor="pink" width="100%">
		<tr bgcolor="cyan">
			<th>아이디</th>
			<th>이름</th>
			<th>주소</th>
			<th>전화번호</th>
			<th>가입일</th>
		</tr>
		<%
		for (int i = 0; i < list.size(); i++) {
			out.println("<tr><td>" + list.get(i).getId());
			out.println("</td><td>" + list.get(i).getName());
			out.println("</td><td>" + list.get(i).getAddress());
			out.println("</td><td>" + list.get(i).getTel());
			out.println("</td><td>" + list.get(i).getReg_date());
			out.println("</td></tr>");
		}
		%>
	</table>
	<%
	}
	%>
</body>
</html>