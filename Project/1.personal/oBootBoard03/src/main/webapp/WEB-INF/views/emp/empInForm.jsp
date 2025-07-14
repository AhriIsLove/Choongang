<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 부트스트랩 CSS CDN 링크 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<style>
/* 폼 컨테이너를 페이지 중앙에 배치하고 최대 너비 설정 (선택 사항) */
.form-container {
	max-width: 600px;
	margin-top: 50px;
	padding: 30px;
	border: 1px solid #dee2e6;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}
</style>
</head>
<body>
	<div id="header">
		<%@ include file="../header.jsp"%>
	</div>
	<div id="contents">
	  <div class="container form-container  bg-primary bg-opacity-25">
        <h2 class="text-center mb-4">직원 등록</h2>
	        <form action="${pageContext.request.contextPath}/emp/saveEmp" method="post" enctype="multipart/form-data">
	            <!-- emp_id -->
	            <div class="row mb-3">
	                <label for="empId" class="col-sm-3 col-form-label form-label-col">직원 ID</label>
	                <div class="col-sm-9">
	                    <input type="text" class="form-control" id="empId" name="emp_id" placeholder="직원 ID를 입력하세요" required>
	                </div>
	            </div>
	 
	            <!-- emp_password -->
	            <div class="row mb-3">
	                <label for="empPassword" class="col-sm-3 col-form-label form-label-col">비밀번호</label>
	                <div class="col-sm-9">
	                    <input type="password" class="form-control" id="empPassword" name="emp_password" placeholder="비밀번호를 입력하세요" required>
	                </div>
	            </div>
	
	            <!-- emp_name -->
	            <div class="row mb-3">
	                <label for="empName" class="col-sm-3 col-form-label form-label-col">직원 이름</label>
	                <div class="col-sm-9">
	                    <input type="text" class="form-control" id="empName" name="emp_name" placeholder="직원 이름을 입력하세요" required>
	                </div>
	            </div>
	
	            <!-- email -->
	            <div class="row mb-3">
	                <label for="email" class="col-sm-3 col-form-label form-label-col">이메일</label>
	                <div class="col-sm-9">
	                    <input type="email" class="form-control" id="email" name="email" placeholder="이메일을 입력하세요 (예: user@example.com)">
	                </div>
	            </div>
	
	            <!-- emp_tel -->
	            <div class="row mb-3">
	                <label for="empTel" class="col-sm-3 col-form-label form-label-col">전화번호</label>
	                <div class="col-sm-9">
	                    <input type="tel" class="form-control" id="empTel" name="emp_tel" placeholder="전화번호를 입력하세요 (예: 010-1234-5678)">
	                </div>
	            </div>
	
	            <!-- sal -->
	            <div class="row mb-3">
	                <label for="sal" class="col-sm-3 col-form-label form-label-col">급여</label>
	                <div class="col-sm-9">
	                    <input type="number" class="form-control" id="sal" name="sal" placeholder="급여를 입력하세요">
	                </div>
	            </div>
	            <!-- del_status (Boolean) - hidden으로 변경 -->
	            <!-- del_status가 false(활성)를 의미한다고 가정하고, 항상 활성 상태로 등록되도록 hidden 필드 사용 -->
	            <input type="hidden" id="delStatus" name="del_status" value="false">
	
	       		<!-- dept_code (int) -->
	            <div class="row mb-3">
	                <label for="deptCode" class="col-sm-3 col-form-label form-label-col">부서 코드</label>
	                <div class="col-sm-9">
	                    <input type="number" class="form-control" id="deptCode" name="dept_code" placeholder="부서 코드를 입력하세요 (숫자)" required>
	                </div>
	            </div>
	
	            <!-- emp_level (int) - 콤보 박스로 변경 -->
<!-- 	            <div class="row mb-3">
	                <label for="empLevel" class="col-sm-3 col-form-label form-label-col">직원 레벨</label>
	                <div class="col-sm-9">
	                    <select class="form-select" id="empLevel" name="emp_level" required>
	                        <option value="">레벨 선택</option> 기본 선택 안내
	                        <option value="1">1(일반)</option>
	                        <option value="2">2(게시판관리자)</option>
	                        <option value="3">3(각부서부서장)</option>
	                        <option value="4">4(임원)</option>
	                        <option value="5">5(사장)</option>
	                    </select>
	                </div>
	            </div>
 -->	
	          	            <!-- files (List<MultipartFile>) -->
	 		    파일선택:<input type="file" name="files" multiple> <p>
	
	            <!-- 폼 제출 버튼 - 한 줄에 배치 -->
	            <div class="d-flex gap-2 mt-4 justify-content-center">
	                <button type="submit" class="btn btn-primary btn-lg">직원 등록</button>
	                <button type="reset" class="btn btn-secondary btn-lg">초기화</button>
	            </div>
	        </form>
		</div>
	</div>
	<div id="footer">
		<%@ include file="../foot.jsp"%>
	</div>
	<!-- 부트스트랩 JS CDN 링크 (<body> 태그 닫기 직전에!) -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>

</body>
</html>