<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--  스크릿틀릿 + Statement +sql문장( String.format) -->
	<!-- Dept Table row Insert -->
	<%
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";

	Class.forName(driver);

	Connection conn = DriverManager.getConnection(url, "scott", "tiger");

	String deptno = request.getParameter("deptno");
	String dname = request.getParameter("dname");
	String loc = request.getParameter("loc");
	String sql = String.format("INSERT INTO dept VALUES (%s, '%s', '%s')", deptno, dname, loc);

	Statement stmt = conn.createStatement();
	int result = stmt.executeUpdate(sql);
	if(result > 0) out.println("입력성공");
	else out.println("입력실패");
	stmt.close();
	conn.close();
	
	%>
</body>
</html>