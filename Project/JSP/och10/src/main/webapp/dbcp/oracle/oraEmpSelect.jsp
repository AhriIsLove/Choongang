<%@page import="och10.Emp"%>
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
	DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/OracleDB");
	Connection conn = ds.getConnection();
	if(conn != null){
		String empno = request.getParameter("empno");
		String sql = "SELECT empno, ename, sal, hiredate FROM emp WHERE empno = " + empno;
		
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		// 1. Emp DTO 선언하고 가져온 값 Setting
		Emp emp = new Emp();
		if(rs.next()){
			emp.setEmpno(rs.getInt(1));
			emp.setEname(rs.getString(2));
			emp.setSal(rs.getInt(3));
			emp.setHiredate(rs.getDate(4));
			
			request.setAttribute("emp", emp);
			// 2. Page이동 oraEmpResult.jsp
			RequestDispatcher rd = request.getRequestDispatcher("oraEmpResult.jsp");
			rd.forward(request, response);
		}
		else
		{
			out.print("없는 사번");
		}
		
		rs.close();
		stmt.close();
	}
	else{
		out.print("연결 실패");
	}
	conn.close();
	%>
</body>
</html>