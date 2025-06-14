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

	// 1. request에 저장 
	// 2. Page 이동 --> my03Result.jsp
	// 3. EL방식의 부서 정보 출력
	
	String dno = request.getParameter("dno");
	
	String sql = "SELECT * FROM division WHERE DNO = " + dno;

	Statement stmt = conn.createStatement();
	ResultSet rs = stmt.executeQuery(sql);

	if (rs.next()) {
		String dname = rs.getString(2);
		String phone = rs.getString(3);
		String position = rs.getString(4);
		
		request.setAttribute("dno", dno);
		request.setAttribute("dname", dname);
		request.setAttribute("phone", phone);
		request.setAttribute("position", position);
		
		RequestDispatcher rd = request.getRequestDispatcher("my03Result.jsp");
		rd.forward(request, response);		
	}

	rs.close();
	stmt.close();
	conn.close();
	%>
</body>
</html>