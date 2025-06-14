<%@page import="och10.Division"%>
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
	
	// dno get --> string dno
	// String sql로 division 가져오는 sql 작성
	String dno = request.getParameter("dno");
	String sql = "SELECT dno, dname, phone, position FROM division WHERE dno = " + dno;
	
	// 1. division DTO 선언
	// 2. Statement & ResultSet 이용 data Get
	// 3. division DTO 에 담기
	// 4. Page 이동 --> my04Result.jsp
	// 5.  my04Result.jsp에서 부서정보 보여주기
	Division division = new Division();
	Statement stmt = conn.createStatement();
	ResultSet rs = stmt.executeQuery(sql);
	
	while(rs.next()){
		division.setDno(rs.getInt(1));
		division.setDname(rs.getString(2));
		division.setPhone(rs.getString(3));
		division.setPosition(rs.getString(4));
		
		request.setAttribute("division", division);
		
		RequestDispatcher rd = request.getRequestDispatcher("my04Result.jsp");
		rd.forward(request, response);
	}
	
	out.println("없는 부서");
	
	rs.close();
	stmt.close();
	conn.close();
%>
</body>
</html>