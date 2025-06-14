<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
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
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";

	Class.forName(driver);

	Connection conn = DriverManager.getConnection(url, "scott", "tiger");

	String deptno = request.getParameter("deptno");
	String sql = "SELECT * FROM dept WHERE deptno = " + deptno;
	System.out.println("sql->" + sql);

	Statement stmt = conn.createStatement();
	ResultSet rs = stmt.executeQuery(sql);

	if (rs.next()) {
		String dname = rs.getString("dname");
		String loc = rs.getString(3);
		out.println("부서코드 : " + deptno + "<p>");
		out.println("부서명 : " + dname + "<p>");
		out.println("근무지 : " + loc + "<p>");
	} else {
		out.println("없는 부서코드");
	}

	rs.close();
	stmt.close();
	conn.close();
	%>
</body>
</html>