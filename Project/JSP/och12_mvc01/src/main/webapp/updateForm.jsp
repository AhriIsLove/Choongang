<%@page import="och12_mvc01.Member"%>
<%@page import="och12_mvc01.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="memberCheck.jsp"%><!-- main.jsp에 들어올떄마다 memberCheck.jsp 실행 : Filter와 같은 용도 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table {
	background: pink;
}
</style>
</head>
<body>
	<%
	Member member = MemberDao.getInstance().select(id); /* 상단의 include file="memberCheck.jsp"에서 id를 가져옴 */
	String context = request.getContextPath();
	%>
	<h1>회원정보 수정</h1>
	<form action="updatePro.jsp">
	<table border="1" id="tbl">
		<tr>
			<td>아이디</td>
			<td><%=id %><input type="hidden" name="id" id="id" class="id" value="<%=member.getId()%>"></td>
		</tr>
		<tr>
			<td>새 암호</td>
			<td><input type="password" name="passwd" id="passwd" required="required"></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" name="name" value="<%=member.getName() %>" required="required"></td>
		</tr>
		<tr>
			<td>주소</td>
			<td><input type="text" name="address" value="<%=member.getAddress() %>" required="required"></td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td><input type="tel" name="tel" pattern="\d{2,3}-\d{3,4}-\d{4}" value="<%=member.getTel() %>" required="required"></td>
		</tr>
		<tr>
			<td>가입날짜</td>
			<td><%=member.getReg_date() %></td>
		</tr>
		<tr>
		<td><input type="submit" value="확인"></td>
		<td><input type="reset" value="취소"></td>
		</tr>
	</table>
	
	</form>
</body>
</html>