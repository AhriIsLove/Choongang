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
	Statement stmt = conn.createStatement();
	
	// 1. dno 받아 division 조회 SQL 작성
	String sql = "SELECT dno , dname , phone , position FROM division WHERE dno = "+ request.getParameter("dno");
	// 2.request 저장 -> dno , dname , phone , position
	ResultSet rs = stmt.executeQuery(sql);
	if(rs.next()){
		request.setAttribute("dno", rs.getInt(1));
		request.setAttribute("dname", rs.getString(2));
		request.setAttribute("phone", rs.getString(3));
		request.setAttribute("position", rs.getString(4));

		// 3. Page이동 -> myDivisionUpdateIn.jsp
		RequestDispatcher rd = request.getRequestDispatcher("myDivisionUpdateIn.jsp");
		rd.forward(request, response);
	}
	else{
		out.print("데이터 없음");
	}
	
	rs.close();
	stmt.close();
	conn.close();
	%>
</body>
</html>