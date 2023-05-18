<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
	$(document).ready(function(){
		
	})		
	
	
</script>
</head>
<body>
	<form>
		글번호:<input type="text" name="boardno" id="boardno"/>
		<input type="button" value="ajax테스트" id="testbtn"/>
		<input type="button" value="ajaxjson테스트" id="testjsonbtn"/>
	</form>
	<div id="result">
		<h4>글번호:<span id="no"></span></h4>
		<h4>제목:<span id="title"></span></h4>
		<h4>작성자:<span id="writer"></span></h4>
		<h4>내용:<span id="content"></span></h4>
	</div>
</body>
</html>