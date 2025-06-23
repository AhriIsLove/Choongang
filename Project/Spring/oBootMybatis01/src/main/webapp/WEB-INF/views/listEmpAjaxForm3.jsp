<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	function getEmpListUpdateTest() {
		var arr = new Array();//수정된 내용 적용을 위한 모든 데이터 배열

		//수정된 현재 값 arr에 저장 시도
		var item;
		<c:forEach items="${listEmp}" var="item">
		arr.push({
			empno : "${item.empno}",
			ename : "${item.ename}",
			deptno : "${item.deptno}"
		});
		</c:forEach>
		//데이터 확인
		//수정된 현재 값이 arr에 들어가지 않음(이전 데이터가 저장됨)
		for (var i = 0; i < arr.length;) {
			console.log("arr.empno." + i + ":" + arr[i].empno + "arr.ename."
					+ i + ":" + arr[i].ename)
			i++;
			if (i > 2)
				return;
		}
	}

	function getEmpListUpdate() {
		//수정된 내용 적용을 위한 모든 데이터 배열
		let empList = [];
		//현재 document(HTML)에서 해당하는 모든 태그(요소)들을 가져온다.
		// - input[name="~"] : input태그 중 name이 "~"인 요소를 가져온다.
		const inputs = document
				.querySelectorAll('input[name="empno"], input[name="ename"], input[name="deptno"]');
		for (let i = 0; i < inputs.length; i += 3) {
// 			console.log("inputs[i]:" + inputs[i].value);
// 			console.log("inputs[i+1]:" + inputs[i+1].value);
// 			console.log("inputs[i+2]:" + inputs[i+2].value);
// 			console.log("inputs[i+3]:" + inputs[i+3].value);
			//JSP에서 input의 순서가 deptno,empno,ename으로 작성되어 순서대로 가져옴
			// - querySelectorAll 순서대로 가져오는게 아님!
			const empno = inputs[i + 1].value;
			const ename = inputs[i + 2].value;
// 			const deptno = inputs[i + 3].value;
			const deptno = inputs[i + 0].value;
			//불러온 값을 JSON으로 만든다.
			const empItem = {
				"empno" : empno,
				"ename" : ename,
				"deptno" : deptno,
			};
			//데이터 전송을 위해 저장
			empList.push(empItem);
			//0, 3, 6, 9, 12 : 5명
			if (i > 10)
				break;
		}
		//데이터를 수정하기 위해 전송한다.
		if (empList.length > 0) {
			$.ajax({
				url : 'empListUpdate',
				contentType : 'application/json',
				data : JSON.stringify(empList),
				method : 'POST',
				dataType : 'json',
				success : function(response) {
					console.log(response.updateResult);
				}
			});
		}
	}
</script>
</head>
<body>
	<h2>회원 정보3</h2>
	<table id="empList">
		<tr>
			<th>번호</th>
			<th>사번</th>
			<th>이름</th>
			<th>업무</th>
			<th>부서</th>
		</tr>
		<c:forEach var="emp" items="${listEmp}" varStatus="status">
			<tr id="empListRow">
				<td>emp${status.index}</td>
				<td>
				<input type="hidden" class="deptno" id="deptno" name="deptno" value="${emp.deptno }" />
				<input type="text" class="empno" id="empno"	name="empno" value="${emp.empno }">${emp.empno }</td>
				<td><input type="text" class="ename" id="ename" name="ename" value="${emp.ename }">${emp.ename }</td>
				<td>${emp.job }</td>
				<td>${emp.deptno }</td>
			</tr>

		</c:forEach>
	</table>

	RestController LISTVO3:
	<input type="button" id="btn_Dept3" value="empLISTTest 전송 " onclick="getEmpListUpdateTest()">
	<p />
	RestController LISTVO3:
	<input type="button" id="btn_Dept3" value="empLIST 전송 " onclick="getEmpListUpdate()">
	<p />
	<h1>The End</h1>
</body>
</html>