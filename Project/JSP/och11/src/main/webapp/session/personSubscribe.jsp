<%@page import="och11.MemberDao"%>
<%@page import="och11.MemberDto"%>
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
	String chk = "";
	String agree = request.getParameter("agree");
	if (agree.equals("y")) {
		String id = (String)session.getAttribute("id");
		String password = (String)session.getAttribute("password");
		String name = (String)session.getAttribute("name");
		
		MemberDto member = new MemberDto();
		MemberDao md = new MemberDao();
		member.setId(id);
		member.setPassword(password);
		member.setName(name);
		
		//INSERT SQL
		int result = md.insert(member);
		if(result > 0) chk = "sucess";
		else chk = "fail";
		
		out.print(session.getId() + "<br>");
		//페이지 이동(chk)
		response.sendRedirect("personResult.jsp?chk=" + chk);
	}
	%>
</body>
</html>