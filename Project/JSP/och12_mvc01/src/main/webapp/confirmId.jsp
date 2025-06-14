<%@page import="och12_mvc01.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
String id = request.getParameter("id");
%>
<script type="text/javascript">
function wincl() {
	//opener : 부모 HTML
	opener.document.frm.id.value = "<%=id%>";
	window.close();//현재 창 닫기
}
</script>
</head>
<body>
<%
int result = MemberDao.getInstance().confirm(id);

if(result == 0){
	%>
	<%=id %>는 사용할 수 있습니다.<p>
	<input type="button" value="닫기" onclick="wincl()">
	<%
}
else{
	%>
	<%=id %>는 이미 있는 아이디입니다. 다른 아이디를 입력해주세요.<p>
	<form action=""><!-- 자기자신에게 이동 -->
		아이디 : <input type="text" name="id"><p>
		<input type="submit" value="확인">
	</form>
	<%
}
%>
</body>
</html>