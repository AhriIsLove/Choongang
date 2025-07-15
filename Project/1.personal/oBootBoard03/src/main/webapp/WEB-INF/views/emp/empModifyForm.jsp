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
        /* 폼 컨테이너를 페이지 중앙에 배치하고 최대 너비 설정 (선택 사항) */
        .form-container {
            max-width: 70%;
            margin: 3px auto;
            padding: 3px;
            background-color: #FFBB00;
            border: 1px solid #dee2e6;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
    </style>
</head>
<body>
	<div id="header">
		<%@ include file="../header.jsp" %>
	</div>
	<div id="contents">
	    <div class="form-container bg-primary bg-opacity-25">
	        <h2 class="text-center mb-4">사원 상세 조회</h2>
	        <!-- 조회 화면이므로 form action은 필요 없음. -->
	        <!-- 만약 수정 페이지로 이동하는 버튼이 있다면, 해당 버튼에 onclick 등으로 처리 -->
			<form action="${pageContext.request.contextPath}/emp/update" method="post">
			    <!-- 사 번 -->
			    <div class="row mb-3">
			        <label for="empNo" class="col-sm-2 col-form-label form-label-col">사 번</label>
			        <div class="col-sm-8">
			            <input type="text" class="form-control" id="empNo" name="emp_no" value="${empDto.emp_no}" readonly>
			        </div>
			    </div>
			    
			    <!-- 사원 ID -->
			    <div class="row mb-3">
			        <label for="empId" class="col-sm-2 col-form-label form-label-col">사원 ID</label>
			        <div class="col-sm-8">
			            <input type="text" class="form-control" id="empId" name="emp_id" value="${empDto.emp_id}">
			        </div>
			    </div>
			
			    <!-- 사원 전화 -->
			    <div class="row mb-3">
			        <label for="empTel" class="col-sm-2 col-form-label form-label-col">사원 전화</label>
			        <div class="col-sm-8">
			            <input type="text" class="form-control" id="empTel" name="emp_tel" value="${empDto.emp_tel}">
			        </div>
			    </div>
			
			    <!-- 사원 비번 -->
			    <div class="row mb-3">
			        <label for="empPassword" class="col-sm-2 col-form-label form-label-col">사원 비번</label>
			        <div class="col-sm-8">
			            <input type="password" class="form-control" id="empPassword" name="emp_password" value="${empDto.emp_password}">
			        </div>
			    </div>
			
			    <!-- 사원 명 -->
			    <div class="row mb-3">
			        <label for="empName" class="col-sm-2 col-form-label form-label-col">사원 명</label>
			        <div class="col-sm-8">
			            <input type="text" class="form-control" id="empName" name="emp_name" value="${empDto.emp_name}">
			        </div>
			    </div>
			
			    <!-- e-Mail -->
			    <div class="row mb-3">
			        <label for="email" class="col-sm-2 col-form-label form-label-col">e-Mail</label>
			        <div class="col-sm-8">
			            <input type="email" class="form-control" id="email" name="email" value="${empDto.email}">
			        </div>
			    </div>
			    
			    <!-- 사원 급여 -->
			    <div class="row mb-3">
			        <label for="sal" class="col-sm-2 col-form-label form-label-col">사원 급여</label>
			        <div class="col-sm-8">
			            <input type="text" class="form-control" id="sal" name="sal" value="${empDto.sal}">
			        </div>
			    </div>
			
			    <!-- 부서 -->
			    <div class="row mb-3">
			        <label for="deptName" class="col-sm-2 col-form-label form-label-col">부서명</label>
			        <div class="col-sm-8">
			            <input type="text" class="form-control" id="deptName" name="dept_name" value="${empDto.dept_name}">
			        </div>
			    </div>
				<!-- 부서 -->
				<div class="row mb-3">
				    <label for="deptCode" class="col-sm-2 col-form-label form-label-col">부서명</label>
				    <div class="col-sm-8">
				        <select class="form-select" id="deptCode" name="dept_code">
				            <c:forEach items="${deptDtoList}" var="dept">
				                <option value="${dept.dept_code}" <c:if test="${dept.dept_code == empDto.dept_code}">selected</c:if>>${dept.dept_name}</option>
				            </c:forEach>
				        </select>
				        <input type="hidden" name="dept_name" value="${empDto.dept_name}">
				    </div>
				</div>			
			
			    <!-- 이미지 표시 영역 -->
			    <div class="row mb-3">
			        <label class="col-sm-2 col-form-label form-label-col">첨부 이미지</label>
			        <div class="col-sm-8">
			            <div class="image-container">
			                <c:forEach items="${empDto.uploadFileNames}" var="fileName">
			                    <img src="${pageContext.request.contextPath}/upload/${fileName}" alt="첨부 이미지" class="uploaded-image" style="max-width: 200px; margin: 5px;">
			                </c:forEach>
			            </div>
			           <!--  새로운 Image 입력 -->
			            <input type="file" class="form-control mt-2" id="imageUpload" name="files" multiple>
			        </div>
			    </div>
			    
			    <!-- 버튼 -->
			    <div class="d-flex gap-2 mt-4 justify-content-center"">
			        <!-- 수정하기 버튼: 폼을 서버로 제출 (type="submit") -->
			        <button type="submit" class="btn btn-primary btn-lg">수정 완료</button>
			        <!-- 목록으로 버튼: 부서 목록 페이지로 이동 (type="button" 유지) -->
			        <button type="button" class="btn btn-secondary btn-lg" onclick="location.href='${pageContext.request.contextPath}/emp/list'">목록으로</button>
			    </div>
			</form>
		</div>
    </div>
    
	<div id="footer">
		<%@ include file="../foot.jsp" %>
	</div>
	<!-- 부트스트랩 JS CDN 링크 (<body> 태그 닫기 직전에!) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

</body>
</html>