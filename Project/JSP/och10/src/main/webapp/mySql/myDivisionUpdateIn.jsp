<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>부서 정보 수정</h1>
	<form action="myUpdate.jsp">
		부서코드 : <input type="hidden" name="dno" value="${dno }">${dno }<p>
		부서명 : <input type="text" name="dname" value="${dname }"><p>
		전화번호 : <input type="text" name="phone" value="${phone }"><p>
		근무지 : <input type="text" name="position" value="${position }"><p>
		<input type="submit" value="수정완료">
		<input type="reset" value="리셋"> <!-- 리셋해도 초기값으로 리셋해주네요? 개꿀! -->
	</form>
</body>
</html>