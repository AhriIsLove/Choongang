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
String info = application.getServerInfo();//Apache Tomcat의 버전 정보
int major = application.getMajorVersion();//Servlet의 버전
int minor = application.getMinorVersion();//Servlet의 상세 버전
String path = application.getRealPath("/");//어플리케이션의 실제 경로(배포시 실제 경로로 수정됨)
%>
<h1>Application 내장객체 예제</h1>
웹 컨테이너 이름과 버전 : <%=info %><p>
서블릿의 버전 : <%=major %>.<%=minor %><p>
웹 어플리케이션 폴더의 로컬 시스템 경로 : <%=path %>
</body>
</html>