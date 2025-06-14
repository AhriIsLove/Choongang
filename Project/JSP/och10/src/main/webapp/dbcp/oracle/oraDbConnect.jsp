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
	<h1>DBCP 연결</h1>
	<%
	//JDNI 컨텍스트 객체 생성
	Context init = new InitialContext();
	
	//lookup => 찾는다.
	//java:comp/env => 환경에서(META_INF/context.xml/<Context>)
	//jdbc.OracleDB라고 name이 정의된 Resource를
	DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
	
	Connection conn = ds.getConnection();//연결 시도
	if(conn != null) out.println("연결 성공");
	else out.println("연결 실패");
	conn.close();
	
	%>
</body>
</html>