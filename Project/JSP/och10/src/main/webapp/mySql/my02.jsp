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
	<%
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/scottdb?serverTimezone=UTC";
	Class.forName(driver);
	Connection conn = DriverManager.getConnection(url, "root", "mysql84");

	/* 
	부서코드 DNO : 20
	부서명 DNAME : 회계
	전화번호 phone : 010-2536-7890
	근무지 POSITION : 대리
	 */

	String dno = request.getParameter("dno");
	String dname = "";
	String phone = "";
	String position = "";
	if (conn != null) {
		String sql = "SELECT * FROM division WHERE DNO = " + dno;

		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		if (rs.next()) {
			dname = rs.getString(2);
			phone = rs.getString(3);
			position = rs.getString(4);
		}

		rs.close();
		stmt.close();
	} else {
		out.println("mysql 연결 실패");
	}

	conn.close();
	%>
	
	부서코드 :
	<%=dno%><p>
	부서명 :
	<%=dname%><p>
	전화번호 :
	<%=phone%><p>
	근무지 :
	<%=position%><p>
</body>
</html>