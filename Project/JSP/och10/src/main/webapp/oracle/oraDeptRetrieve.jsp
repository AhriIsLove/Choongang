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
<!-- 
1. deptno 를 가지고 dept 조회
2. deptno ,dname , loc : request.setAttribute
3. oraDeptIn.jsp Page이동
    EL : deptno ,dname , loc 
 -->
<%
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";

	Class.forName(driver);

	Connection conn = DriverManager.getConnection(url, "scott", "tiger");

	String deptno = request.getParameter("deptno");
	String sql = String.format("SELECT deptno ,dname , loc FROM dept WHERE deptno = " + deptno);

	Statement stmt = conn.createStatement();
	ResultSet rs = stmt.executeQuery(sql);
	
	if(rs.next()){
		String dname = rs.getString(2);
		String loc = rs.getString(3);

		request.setAttribute("deptno", deptno);
		request.setAttribute("dname", dname);
		request.setAttribute("loc", loc);
		RequestDispatcher rd = request.getRequestDispatcher("oraDeptIn.jsp");
		rd.forward(request, response);
	}
	
	rs.close();
	stmt.close();
	conn.close();
%>
<script type="text/javascript">
alert("없는 부서 입니다.");
location.href = "oraDeptUpdate.html";
</script>
</body>
</html>