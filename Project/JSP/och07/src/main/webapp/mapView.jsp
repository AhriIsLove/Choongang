<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
${param.NAME }의 주소는 ${Address[param.NAME] }<p>
${Address.Jasica }<p>
${Address.Susan }<p>
${Address["Susan"] }<p>
${Address["이승희"] }<p>
</body>
</html>