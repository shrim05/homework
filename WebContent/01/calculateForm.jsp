<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>calculate Form</title>
<script type="text/javascript">
</script>
</head>
<body>
<!-- 과제명: 사칙연산기 -->
<!-- 1. homework 컨텍스트로 프로젝트 생성. -->
<!-- 2. webStudy01 대신 homework에서 작업 -->
<!-- 3. 연산의 결과는 plain으로 전달--> 
<form name="frm" action="/homework/calculator" method="post">
<!-- 사칙연산 수행 -->
	연산을 수행하는 클라이언트의 이름 (한글) : <input type="text" name="name"/>
	<br>
	<input type="number" name="leftOp" />
	<select name="operator">
		<option value="PLUS">+</option>
		<option value="MINUS">-</option>
		<option value="MULTIPLY">*</option>
		<option value="DIVIDE">/</option>
	</select>

	<input type="number" name="rightOp" />
	<button type="submit">=</button>
</form>
<%-- 	<input type="text" style="width:500px;" value="<%=request.getAttribute("result") %>"> --%>
	<input type="text" style="width:500px;" value="${result}">
</body>
</html>