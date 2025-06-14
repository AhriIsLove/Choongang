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
	<h2>스크릿틀릿+PreparedStatement HW01</h2>
	<%
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/scottdb?serverTimezone=UTC";
	Class.forName(driver);
	Connection conn = DriverManager.getConnection(url, "root", "mysql84");
	
	// 1. dno,dname,phone,position Get
    // 2. Update
    //    1) 성공 -> mysql 수정 성공 ㅋㅋ
    //    2) 실패 -> mysql 수정 헉 ㅠㅠ
	String sql = "UPDATE division SET dname = ?, phone = ?, position = ? WHERE dno = ?";
	PreparedStatement pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, request.getParameter("dname"));
	pstmt.setString(2, request.getParameter("phone"));
	pstmt.setString(3, request.getParameter("position"));
	pstmt.setString(4, request.getParameter("dno"));
	
	int result = pstmt.executeUpdate();
	if(result > 0){
		out.print("mysql 수정 성공 ㅋㅋ");
	}else{
		out.print("mysql 수정 헉 ㅠㅠ");
	}
	
	%>
</body>
</html>