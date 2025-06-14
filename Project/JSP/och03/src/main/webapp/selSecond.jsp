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
String[] col={"red","orange","yellow","green","blue","navy","violet","black"};
int n = (int)(Math.random() * 8);

String pgm = request.getParameter("pgm") + ".jsp";
System.out.println("pgm->"+pgm);

//Page 이동
RequestDispatcher rd = request.getRequestDispatcher(pgm);
if(pgm.equals("color1.jsp")){
	request.setAttribute("color", col[n]);
}
else if(pgm.equals("gugu1.jsp")){
	request.setAttribute("num", n+2);//2~9
}
else if(pgm.equals("response.jsp")){
	//필요시 작성
	//response.sendRedirect("http://www.choongang.co.kr/fordeveloper");
}
rd.forward(request, response);
%>
</body>
</html>