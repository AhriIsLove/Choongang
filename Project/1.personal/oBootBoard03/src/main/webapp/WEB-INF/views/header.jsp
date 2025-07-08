<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- Bootstrap Navbar -->
	<nav class="navbar navbar-expand-md bg-body-tertiary">
		<div class="container-fluid">
			<a class="navbar-brand" href="/main">HOME</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
				aria-controls="navbarNavDropdown" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNavDropdown">
				<ul class="navbar-nav">
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" role="button"
						data-bs-toggle="dropdown" aria-expanded="false">부서관리</a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="<%=request.getContextPath()%>/dept/list">부서 조회</a></li>
							<li><a class="dropdown-item" href="<%=request.getContextPath()%>/dept/deptInForm">부서 등록</a></li>
						</ul></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" role="button"
						data-bs-toggle="dropdown" aria-expanded="false">사원관리</a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="<%=request.getContextPath()%>/emp/list">사원 조회</a></li>
							<li><a class="dropdown-item" href="#">사원 등록</a></li>
						</ul></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" role="button"
						data-bs-toggle="dropdown" aria-expanded="false">게시판관리</a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="#">게시판 조회</a></li>
							<li><a class="dropdown-item" href="#">게시판 등록</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</nav>
</body>
</html>