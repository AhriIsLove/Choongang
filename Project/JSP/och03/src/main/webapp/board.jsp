<%@page import="java.io.FileWriter"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>게시판</h1>
	<%
	String title = request.getParameter("title");
	String writer = request.getParameter("writer");
	String content = request.getParameter("content");
	String msg = "제목 : " + title + "/r/n";
	msg += "작성자 : " + writer + "/r/n";
	msg += "내용 : " + content + "/r/n";
	
	Date date = new Date();
	long fileName = date.getTime();
	String real = application.getRealPath("/WEB-INF/out/" + fileName + ".txt");
	System.out.println(real);
	FileWriter fw = new FileWriter(real);
	fw.write(msg);
	fw.close();
	
	out.println("제목 : " + title + "<br>");
	out.println("작성자 : " + writer + "<br>");
	out.println("내용 : " + content + "<br>");
	%>
</body>
</html>