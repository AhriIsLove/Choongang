<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>다양한 Ajax Test</h1>
	<h3>EmpRestController</h3>
	<a href="/helloText">helloText</a>
	<p />
	<a href="/sample/sendVO2?deptno=123">sample/sendVO2(객체) EmpRestController</a>
	<p />
	<a href="/sendVO3">sendVO3</a>
	<p />

	<h3>AjaxController</h3>
	<a href="/getDeptName?deptno=10">getDeptName(EmpController)</a>
	<p />
	<a href="/listEmpAjaxForm">listEmpAjaxForm(Ajax JSP 연동)</a>
	<p />
	<a href="/listEmpAjaxForm2">listEmpAjaxForm2(Ajax JSP 객체리스트 Get)</a>
	<p />
	<a href="/listEmpAjaxForm3">listEmpAjaxForm3(Ajax List를 Controller로 전송)</a>
	<p />
</body>
</html>