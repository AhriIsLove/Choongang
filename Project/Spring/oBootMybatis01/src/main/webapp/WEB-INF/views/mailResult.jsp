<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Mail 전송 결과</h1>
<c:if test="${check==1 }">메일 전송 성공(～￣▽￣)～</c:if>
<c:if test="${check!=1 }">메일 전송 실패1(´。＿。｀)</c:if>
<c:if test="${check==null }">메일 전송 실패2Σ(っ °Д °;)っ</c:if>
</body>
</html>