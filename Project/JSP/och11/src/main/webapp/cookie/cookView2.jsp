<%@page import="java.net.URLDecoder"%>
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
	Cookie cooks[] = request.getCookies();
	if(cooks != null){
		for(int i=0; i<cooks.length; i++){
			if(cooks[i].getName().equals("name")){
				out.println("쿠키값 : " + URLDecoder.decode(cooks[i].getValue(), "UTF-8"));
			}
		}
	}
	%>
	쿠키 확인 방법 : F12(검사) -> Application -> Storage -> Cookies
</body>
</html>