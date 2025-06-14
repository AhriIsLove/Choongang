<%@page import="java.io.FileWriter"%>
<%@page import="org.apache.catalina.filters.ExpiresFilter.XServletOutputStream"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%!
	//Method , Member변수   --> init
	private PrintWriter pw;
	String date;
	
	public void jspInit(){
		GregorianCalendar gc = new GregorianCalendar();
		date = String.format("%TF %TT", gc, gc);
		System.out.println("jspInit date->"+date);
		String fileName = "C:/log/" + date.replaceAll(":", "") + ".txt";
		try {
			pw = new PrintWriter(new FileWriter(fileName, true));
		} catch (Exception e) {
			System.out.println("대박 ! 으이그");
		}
	}
	
	%>
	<%
	//doGet / doPost
	String name = request.getParameter("name");
	System.out.println(name + "님의 사회활동...");
	String msg = name + "님 반가워\r\n";

	out.println(msg + "<p> 현재시간 : " + date);
	pw.println(msg + "\r\n 현재시간 : " + date + "\r\n");
	%>
	<%!
	public void jspDestroy(){
		System.out.println("greet 유언활동");
		pw.flush();
		if(pw!=null){
			pw.close();
		}
	}
	%>
</body>
</html>