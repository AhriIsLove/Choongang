<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
function getDeptName(pDeptno){
	console.log(pDeptno);
	//비동기 요청
	$.ajax({
		//URL : Controller, API
		url:"<%=request.getContextPath()%>/getDeptName",
		//Param : key,value
		data:{deptno:pDeptno},
		//returnType
		dataType:'text',
		//성공시 function 실행
		success:function(deptName){
			//ID가 deptName인 태그를 찾아서 value를 deptName로 변경
			$('#deptName').val(deptName);
			//ID가 msg인 태그를 찾아서 태그 내부를 deptName로 변경
			$('#msg').html(deptName);
		}
	});
}

function getDept(pDeptno){
	$.ajax({
		url:"sample/sendVO2",
		data:{deptno:pDeptno},
		dataType:'json',
		success:function(sampleVo){
			resultStr = sampleVo.firstName + " " + sampleVo.lastName + " " + sampleVo.mno;
			$('#RestDept').val(resultStr);
		}
	});
}

function empWriteBtn(){
	//id가 empTrans인 태그(form)의 값들을 배열로 가져온다.
	var sendData = $('#empTrans').serializeArray();
	//배열을 Json형태로 바꾼다.
	var sendData3 = jsonParse(sendData);
	console.log(sendData3);
	
	$.ajax({
		//Controller, API에
		url:'empSerializeWrite',
		//POST형식으로
		type:'POST',
		//파라미터 타입 : json
		contentType:'application/json;charset=UTF-8',
		//파라미터 값
		data:JSON.stringify(sendData3),
		//리턴 타입 : json
		dataType:'json',
		//리턴 값 : response
		success:function(response){
			console.log("response : " + response);
			if(response.writeResult > 0){
				alert("성공");
				console.log(response);
			}
		}
	});
}
//배열을 Json형태로 바꾼다.
function jsonParse(pSendData){
	//return용 Json형태
	var obj = {};
	if(pSendData){
		//pSendData의 개수만큼 반복
		jQuery.each(pSendData, function(){
			console.log(this.name + ":" + this.value);
			//Json에 Key,Value로 넣는다.
			obj[this.name] = this.value;
		});
	}
	return obj;
}
</script>
</head>
<body>
	<h2>회원 정보</h2>
	<table>
		<tr>
			<th>사번</th>
			<th>이름</th>
			<th>업무</th>
			<th>부서</th>
			<th>근무지</th>
		</tr>
		<c:forEach var="emp" items="${listEmp}">
			<tr>
				<td>${emp.empno }</td>
				<td>${emp.ename }</td>
				<td>${emp.job }</td>
				<td>${emp.deptno}<input type="button" id="btn_idCheck" value="부서명" onmouseover="getDeptName(${emp.deptno })"></td>
				<td>${empDept.loc }</td>
			</tr>
		</c:forEach>
	</table>
	deptName:
	<input type="text" id="deptName" readonly="readonly">
	<p />
	Message :
	<span id="msg"></span>
	<p />
	RestController sendVO2:
	<input type="text" id="RestDept" readonly="readonly">
	<p />
	RestController sendVO2: sendVO2
	<input type="button" id="btn_Dept" value="부서명" onclick="getDept(10)">
	<p />
	<h2>Serialize Test</h2>
	<form name="empTrans" id="empTrans">
		<input type="hidden" id="empno" name="empno" value="1234">
		<input type="hidden" id="ename" name="ename" value="시리얼">
		<input type="hidden" id="sal" name="sal" value="1000">
		<input type="button" value="Ajax Serialize 확인" onclick="empWriteBtn()">
	</form>

</body>
</html>