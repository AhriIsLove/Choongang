<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>EL 부서 정보</h1>
<!-- 
EL 부서정보
부서코드 : 30
부서명 : 경영
전화번호 : 010-7236-7890
근무지 : 차장 
-->
부서코드 : ${dno }<p>
부서명 : ${dname }<p>
전화번호 : ${phone }<p>
근무지 : ${position } <p>

</body>
</html>