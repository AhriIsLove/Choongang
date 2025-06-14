<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="och10.Professor"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	
	ArrayList<Professor> al = new ArrayList<Professor>();
	String sql = "SELECT profno, name FROM professor";

	Statement stmt = conn.createStatement();
	ResultSet rs = stmt.executeQuery(sql);

	while (rs.next()) {
		Professor prof = new Professor();
		prof.setProfno(rs.getInt(1));
		prof.setName(rs.getString(2));

		al.add(prof);
	}
	
	request.setAttribute("al", al);

	rs.close();
	stmt.close();
	conn.close();
	%>
	<h1>보고싶은 교수 번호 선택하세요</h1>
	<form action="myProfSelect.jsp">
		<select name="pno">
			<c:forEach var="professor" items="${al }">
				<option value="${professor.profno }">${professor.profno } ${professor.name }</option>
			</c:forEach>
		</select><p>
		<input type="submit" value="선택완료">
	</form>
</body>
</html>