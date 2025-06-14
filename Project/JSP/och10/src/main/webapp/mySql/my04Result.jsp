<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>EL 부서정보</h1>
부서코드 : ${division.getDno() }<p>
부서명 : ${division.getDname() }<p>
전화번호 : ${division.getPhone() }<p>
근무지 : ${division.getPosition() }<p>
</body>
</html>