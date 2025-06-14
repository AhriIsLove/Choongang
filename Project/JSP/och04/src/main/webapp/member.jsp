<%@page import="java.io.FileWriter"%>
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
String strID = request.getParameter("id");
String strPassword = request.getParameter("password");
String strName = request.getParameter("name");
String[] arrStrHobby = request.getParameterValues("hobby");
String strHobbys = "";
for(String hobby : arrStrHobby){
	strHobbys += hobby + " ";
}
String strGender = request.getParameter("gender");

String strContent = "회원정보\n";
strContent += "아이디 : " + strID + "\n";
strContent += "암호 : " + strPassword + "\n";
strContent += "이름 : " + strName + "\n";
strContent += "취미 : " + strHobbys + "\n";
strContent += "성별 : " + strGender + "\n";

//파일로 만들기
String path = application.getRealPath("/WEB-INF/file/" + strID + ".txt");
FileWriter fw = new FileWriter(path);
fw.write(strContent);
fw.close();
%>
<h1>회원정보</h1>
아이디 : <%=strID %><p> 
암호 : <%=strPassword %><p>
이름 : <%=strName %><p>
취미 : <%=strHobbys %><p>
성별 : <%=strGender %><p>

파일경로 : <%=path %>

</body>
</html>