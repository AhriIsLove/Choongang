<%@page import="och10.Emp"%>
<%@page import="java.util.ArrayList"%>
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
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";

	Class.forName(driver);

	Connection conn = DriverManager.getConnection(url, "scott", "tiger");

	String deptno = request.getParameter("deptno");
	String sql = "SELECT empno, ename, job, sal FROM emp";
	System.out.println("sql->" + sql);

	Statement stmt = conn.createStatement();
	ResultSet rs = stmt.executeQuery(sql);
	
	ArrayList<Emp> al = new ArrayList<Emp>();
	
	if (rs.next()) {
		do{
			Emp emp = new Emp();
			emp.setEmpno(rs.getInt(1));
			emp.setEname(rs.getString(2));
			emp.setJob(rs.getString(3));
			emp.setSal(rs.getInt(4));
			al.add(emp);
		}while(rs.next());
		
		request.setAttribute("al", al);

		rs.close();
		stmt.close();
		conn.close();
		
		// 2. RequestDispatcher --> ora03Result.jsp Page 이동
		//RequestDispatcher rd = request.getRequestDispatcher("ora05Mvc01.jsp");
		RequestDispatcher rd = request.getRequestDispatcher("ora05Mvc02.jsp");
		rd.forward(request, response);
			
	} else {
		out.println("데이터가 없습니다.");
		
		//rs.close();
		stmt.close();
		conn.close();
	}
	%>
</body>
</html>