<%@page import="och10.Emp"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.*"%>
<%@page import="javax.sql.*"%>
<%@page import="javax.naming.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	//ArrayList<Emp> al 만들기
	ArrayList<Emp> al = new ArrayList<Emp>();
	
	// 1. DBCP
	Context ctx = new InitialContext();
	DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/OracleDB");
	Connection conn = ds.getConnection();
	Statement stmt = conn.createStatement();
	
	// 2. emp.empno , emp.ename
	String sql = "SELECT empno, ename FROM emp ORDER BY empno";
	ResultSet rs = stmt.executeQuery(sql);
	while(rs.next()){
		Emp emp = new Emp();
		emp.setEmpno(rs.getInt(1));
		emp.setEname(rs.getString(2));
		
		al.add(emp);
	}
	
	// 3. setAttribute("al", al)
	request.setAttribute("al", al);

	System.out.println("al.size() -> " + al.size());
	
// 	RequestDispatcher rd = request.getRequestDispatcher("");
// 	rd.forward(request, response);

	rs.close();
	stmt.close();
	conn.close();
	%>
	<h1>보고싶은 사원 번호를 선택하세요</h1>
	<form action="oraCallEmpInfo.jsp">
		<select name="empno">
<%-- 			<c:forEach var="emp" items="<%=al %>"> --%>
			<c:forEach var="emp" items="${al }">
				<option value="${emp.empno }">${emp.empno } ${emp.ename }</option>
			</c:forEach>
		</select><p>
		<input type="submit" value="선택완료">
	</form>
</body>
</html>