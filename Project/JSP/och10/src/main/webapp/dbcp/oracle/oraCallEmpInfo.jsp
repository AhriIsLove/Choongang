<%@page import="java.sql.Types"%>
<%@page import="java.sql.CallableStatement"%>
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
	// 1. DBCP
	Context ctx = new InitialContext();
	DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/OracleDB");
	Connection conn = ds.getConnection();
	Statement stmt = conn.createStatement();

	//call 프로시저  : Emp_Info3(in empno, out sal);
	String sql = "{call Emp_Info3(?, ?)}";
	CallableStatement cstmt = conn.prepareCall(sql);
	
	int empno = Integer.parseInt(request.getParameter("empno"));
	System.out.println("empno->"+empno);
	//급여
	cstmt.registerOutParameter(2, Types.DOUBLE);
	cstmt.setInt(1, empno);
	cstmt.execute();
	out.println("급여 : " + cstmt.getDouble(2));
	cstmt.close();
	conn.close();
%>
</body>
</html>