<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="result1"></div>
	<div id="result2"></div>
	<script type="text/javascript">
		// 텍스트형식으로 JSON이 전달되는 경우
		var deptlist = '{"dept":' 
			+'[{"code":"001","name":"정보시스템팀","loc":"4층"},'
			+'{"code":"002","name":"시스템지원팀","loc":"7층"},'
			+'{"code":"003","name":"기업영업팀","loc":"6층"},'
			+'{"code":"004","name":"전산실","loc":"5층"}'
			+']'	
			+ '}';
			
		// 자바스크립트의 JSON 파서를 이용해서 파싱하여 JSON 객체로 만들고 작업하기
		var obj = JSON.parse(deptlist);
		alert(deptlist); // 문자열
		alert(obj); // JSON 객체로 변환
		
		mydata1 = obj.dept[1].code+","+obj.dept[1].name;
		mydata2 = obj.dept[3].code+","+obj.dept[3].name;
		
		/* alert(mydata1);
		alert(mydata2); */
		document.getElementById("result1").innerHTML = mydata1;
		document.getElementById("result2").innerHTML = mydata2;
</script>

</body>
</html>