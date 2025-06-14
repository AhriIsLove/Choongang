<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="error.jsp"%>
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
	<!-- 게시글 목록 -->
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
					<!-- 번호 -->
					<td>${startNum }</td>
					<!-- 제목 -->
					<td class="left" width=200>
						<!-- Hot 게시글 : 조회수 20 초과 -->
						<c:if test="${board.readcount > 20 }">
							<img alt="" src="images/hot.gif" onmouseover="getDeptName(${board.num})">
						</c:if>
						<!-- 답변 게시글 구분 -->
						<c:if test="${board.re_level > 0}">
							<img alt="" src="images/level.gif" width="${board.re_level*10 }">
							<img alt="" src="images/re.gif">
						</c:if>
						<!-- 게시글 링크 -->
						<a href="content.do?num=${board.num }&pageNum=${currentPage}">${board.subject }</a>
						<!-- 보드를 넘기면 더 좋지 않을까? -->
						<!-- getParameter가 String으로만 받아진다. -->
						<%-- <a href="content.do?board=${board }">${board.subject }</a> --%>
					</td>
					<!-- 작성자 -->
					<td>${board.writer }</td>
					<!-- 이메일 -->
					<td><a href="mailto:${board.email }">${board.email }</a></td>
					<!-- IP -->
					<td>${board.ip }</td>
					<!-- 작성일 -->
					<td>${board.reg_date }</td>
					<!-- 조회수 -->
					<td>${board.readcount }</td>
				</tr>
				<c:set var="startNum" value="${startNum - 1 }"></c:set>
			</c:forEach>
		</c:if>
		<!-- 게시글이 없는 경우 -->
		<c:if test="${totCnt  == 0}">
			<tr>
				<td colspan="7">데이터가 없습니다.</td>
			</tr>
		</c:if>
	</table>
	<!-- 페이지 전환 행 -->
	<div style="text-align: center;">
		<c:if test="${startPage > blockSize }">
			<a href="list.do?pageNum=${startPage-blockSize }">[이전]</a>
		</c:if>
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<a href="list.do?pageNum=${i }">[${i }]</a>
		</c:forEach>
		<c:if test="${endPage > blockSize }">
			<a href="list.do?pageNum=${startPage+blockSize }">[다음]</a>
		</c:if>
	</div>
</body>
</html>