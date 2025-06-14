<%@page import="och10.Professor"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
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
	Context ctx = new InitialContext();
	DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/MySql");
	Connection conn = ds.getConnection();
	Statement stmt = conn.createStatement();
	
	// 1. pno Get 하여 해당 pno의
	String pno = request.getParameter("pno");
    // 2. profno, name, sal, hiredate (pno에 맞는) Select
    String sql = "SELECT profno, name, sal, hiredate FROM professor WHERE profno = " + pno;
    ResultSet rs = stmt.executeQuery(sql);
    if(rs.next()){
    	// 3.  Professor DTO --> professor
    	Professor professor = new Professor();
    	professor.setProfno(rs.getInt(1));
    	professor.setName(rs.getString(2));
    	professor.setSal(rs.getInt(3));
    	professor.setHiredate(rs.getDate(4));
    	
    	// 4. setAttribute("professor", professor);
    	request.setAttribute("professor", professor);
    }
    
    // 5. myProfResult.jsp 이동
    RequestDispatcher rd = request.getRequestDispatcher("myProfResult.jsp");
    rd.forward(request, response);
    
    rs.close();
    stmt.close();
    conn.close();
	%>
</body>
</html>