<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="style.css" type="text/css">
<style type="text/css">
table {
	width: 80%;
}
</style>
</head>
<body>
	<h1>게시판</h1>
	<h5>전체건수 : ${totCnt }</h5>
	<table>
		<tr>
			<td><a href="writeForm.do">글쓰기</a></td>
		</tr>
	</table>
	<table>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>이메일</th>
			<th>IP</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<c:if test="${totCnt > 0 }">
			<c:forEach var="board" items="${boardList }">
				<tr>
					<td>${startNum }</td>
					<td class="left" width=200><c:if
							test="${borad.readcount > 20 }">
							<img alt="" src="images/hot.gif"
								onmouseover="getDeptName(${board.num})">
						</c:if>
						<c:if
							test="${borad.re_level > 0 }">
							<img alt="" src="images/level.gif" width="${board.re_level*10 }">
							<img alt="" src="images/re.gif">
						</c:if>
						<a href="content.do?num=${board.num }&pageNum=${currentPage)">${board.subject }</a>
						</td>
						<td>${board.writer }</td>
						<td><a href="mailto:${board.email }">${board.email }</a></td>
						<td>${board.ip }</td>
						<td>${board.reg_date }</td>
						<td>${board.readcount }</td>
				</tr>
				<c:set var="startNum" value="${startNum - 1 }"></c:set>
			</c:forEach>
		</c:if>
	</table>
</body>
</html>