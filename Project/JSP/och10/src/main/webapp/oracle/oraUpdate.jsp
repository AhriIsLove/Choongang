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
	<h2>HW02 스크릿틀릿 + PreparedStatement 수정</h2>
	<%
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";

	Class.forName(driver);

	Connection conn = DriverManager.getConnection(url, "scott", "tiger");

	String deptno = request.getParameter("deptno");
	String sql = String.format("UPDATE dept SET dname = ?, loc = ? WHERE deptno = ?");

	PreparedStatement stmt = conn.prepareStatement(sql);
	stmt.setString(1, request.getParameter("dname"));
	stmt.setString(2, request.getParameter("loc"));
	stmt.setInt(3, Integer.parseInt(request.getParameter("deptno")));
	
	int result = stmt.executeUpdate();//여기
	
	if(result > 0){
		out.println("수정성공");
	}
	else{
		out.println("수정실패");
	}

	stmt.close();
	conn.close();
	%>
</body>
</html>