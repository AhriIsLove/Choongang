<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <!-- 부트스트랩 CSS CDN 링크 -->
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        /* 추가적인 스타일 */
        .list-item-row {
            transition: background-color 0.3s ease; /* 배경색 변화를 부드럽게! */
        }

        .list-item-row:hover {
            background-color: #e2f3ff; /* 마우스 오버 시 연한 파란색으로 변경! */
            /* 아니면 부트스트랩 기본 색상인 #e9ecef (bg-light 색상)도 괜찮아! */
            /* background-color: #e9ecef; */
        }
    </style>
</head>
<body>
	<div id="header">
		<%@ include file="../header.jsp" %>
	</div>
	<div id="contents ">
	    <div class="container my-5 bg-primary bg-opacity-25">
	        <h2 class="text-center mb-4">부서 목록</h2>
	
	        <!-- 목록 헤더 (레이블) -->
	        <div class="row fw-bold py-2 border-bottom bg-dark bg-opacity-25">
	            <div class="col-3">부서 코드</div>
	            <div class="col-3">부서 이름</div>
	            <div class="col-3">전화번호</div>
	            <div class="col-3">위치</div>
	        </div>
	
	        <!-- 데이터 목록 -->
	        <c:forEach var="deptDto" items="${deptDtoList }">
	            <div class="row py-2 border-bottom list-item-row">
	                <div class="col-3">${deptDto.dept_code }</div>
	                <div class="col-3">${deptDto.dept_name }</div>
	                <div class="col-3">${deptDto.dept_tel }</div>
	                <div class="col-3">${deptDto.dept_loc }</div>
	            </div>
	        </c:forEach>
	
	        <!-- 목록이 비어있을 경우 메시지 -->
	        <c:if test="${empty deptDtoList}">
	            <div class="row">
	                <div class="col text-center py-4 text-muted">등록된 부서가 없습니다.</div>
	            </div>
	        </c:if>
		</div>
		<!-- 페이징 -->
		<nav aria-label="Page navigation">
		    <ul class="pagination justify-content-center">
		      <c:if test="${page.startPage > page.pageBlock}">
		        <li class="page-item">
		          <a class="page-link" href="/dept/list?currentPage=${page.startPage - page.pageBlock}" aria-label="Previous">
		            &laquo; 이전
		          </a>
		        </li>
		      </c:if>
		
		      <c:forEach var="i" begin="${page.startPage}" end="${page.endPage}">
		        <li class="page-item ${i == page.currentPage ? 'active' : ''}">
		          <a class="page-link" href="/dept/list?currentPage=${i}">${i}</a>
		        </li>
		      </c:forEach>
		
		      <c:if test="${page.endPage < page.totalPage}">
		        <li class="page-item">
		          <a class="page-link" href="/dept/list?currentPage=${page.startPage + page.pageBlock}" aria-label="Next">
		            다음 &raquo;
		          </a>
		        </li>
		      </c:if>
		    </ul>
		 </nav>
	</div>
		
	</div>
	
	<div id="footer">
		<%@ include file="../foot.jsp" %>
	</div>
	<!-- 부트스트랩 JS CDN 링크 (<body> 태그 닫기 직전에!) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
	
</body>
</body>
</html>