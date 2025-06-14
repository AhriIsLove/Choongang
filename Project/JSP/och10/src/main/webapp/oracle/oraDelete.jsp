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
<h1>스크릿틀릿 + Statement 삭제 HW01</h1>
<%
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";

	Class.forName(driver);

	Connection conn = DriverManager.getConnection(url, "scott", "tiger");

	String deptno = request.getParameter("deptno");
	String sql = "DELETE FROM dept WHERE deptno = " + deptno;
	System.out.println("sql->" + sql);

	Statement stmt = conn.createStatement();
	int r = stmt.executeUpdate(sql);
	
	if(r > 0){
		out.println("삭제 성공");
	}
	else {
		out.println("삭제 실패");
	}
	stmt.close();
	conn.close();
	%>
</body>
</html>