<%@page import="java.util.ArrayList"%>
<%@page import="och10.Professor"%>
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
	
	// 1. Professor --> profno,name,position,sal
	String sql = "SELECT profno,name,position,sal FROM Professor";
	ResultSet rs = stmt.executeQuery(sql);

	// 2. 모든 Professor Row  --> ArrayList<Professor>    al 저장 
	// 3. my05Result.jsp
	ArrayList<Professor> al = new ArrayList<Professor>();
	while(rs.next()){
		Professor professor = new Professor();
		professor.setProfno(rs.getInt(1));
		professor.setName(rs.getString(2));
		professor.setPosition(rs.getString(3));
		professor.setSal(rs.getInt(4));
		
		al.add(professor);
	}
	
	if(al.size() != 0){
		request.setAttribute("al", al);
		
// 		RequestDispatcher rd = request.getRequestDispatcher("my05Model01.jsp");
		RequestDispatcher rd = request.getRequestDispatcher("my05Model02.jsp");
		rd.forward(request, response);
	}else{
		out.println("데이터가 없습니다.");
	}
	
	rs.close();
	stmt.close();
	conn.close();
	%>
</body>
</html>