<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
function getEmpnoDelete(pIndex)  {
	// url    -> empnoDelete(EmpRestController)
	// parm   -> empno : selEmpno 
	// 성공하면  ->  Delete Tag 
	var selEmpno =   $("#empno"+pIndex).val();
	var selEname =   $("#ename"+pIndex).val();
	
    // 결과를 text로 받음 
// 	$.ajax({
// 		url:"/empnoDelete",
// 		data:{  empno : selEmpno 
// 			  , ename : selEname
// 			 },
// 		dataType:'text',
// 		success:function(data){
// 			alert(".ajax getDeptDelete data->"+data); 
// 			if (data == '1') {
// 				// 성공하면 아래라인 수행 --> Delete Tag 
// 				$('#emp'+pIndex).remove();     
// 			}
// 		}
// 	});
	// 결과를 객체로 받음 
	$.ajax({
		url:"/empnoDelete03",
		data:{  empno : selEmpno 
			  , ename : selEname
			 },
		dataType:'json',
		success:function(response){
			alert(".ajax getDeptDelete data->"+response.delStatus); 
			if (response.delStatus == '1') {
				// 성공하면 아래라인 수행 
				$('#emp'+pIndex).remove();/*  Delete Tag */
			}
		}
	});
}

function getListDept(){
	var str = "";//<select>생성 str
	var str2 = "";//<option>생성 str
	
	$.ajax({
		url:"/sendVO3",
		dataType:'json',
		success:function(deptList){
			//json -> string
			var jsonStr = JSON.stringify(deptList);
// 			console.log(jsonStr);
// 			$('#dept_list_str').append(jsonStr);
			//콤보박스로 생성
			str += "<select name = 'dept'";
			$(deptList).each(function(){
				str2 = "<option value='"+this.deptno+"'>"+this.dname+"</option>";
				str += str2;
			})
			str += "</select><p/>"
			//id가 dept_list_combobox인 div태그에 추가
			//.html(str)은 덮어쓰기
			$('#dept_list_combobox').append(str);
		}
	});
}
</script>
</head>
<body>
	<h2>회원 정보</h2>
	<table>
		<tr>
			<th>번호</th>
			<th>사번</th>
			<th>이름</th>
			<th>업무</th>
			<th>부서</th>
		</tr>
		<!-- 		varStatus="status" -->
		<!-- 		- index를 사용 할 수 있도록 함 -->
		<c:forEach var="emp" items="${listEmp}" varStatus="status">
			<tr id="emp${status.index}">
				<td>emp${status.index}</td>
				<td><input type="hidden" id="deptno${status.index}" value="${emp.deptno }"> <input type="text" id="empno${status.index}"
						value="${emp.empno }"></td>
				<td><input type="text" id="ename${status.index}" value="${emp.ename }"></td>
				<td>${emp.job }</td>
				<td>${emp.deptno }<input type="button" id="btn_idCheck2" value="사원 Row Delete" onclick="getEmpnoDelete(${status.index})">
				</td>
			</tr>
		</c:forEach>

	</table>


	RestController LISTVO3:
	<input type="button" id="btn_Dept3" value="부서명 LIST" onclick="getListDept()">
	<p />
	dept_list_str:
	<div id="dept_list_str"></div>
	dept_list_combobox:
	<div id="dept_list_combobox"></div>

	<h1>The End</h1>
</body>
</html>