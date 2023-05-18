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
		// id가 testbtn 인 버튼을 눌렀을 때 /erp/ajax/exam01 을 요청
		// -> json object 가 response
		$("#testbtn").on("click", function() {
			var querydata = {"boardno":$("#boardno").val()}
			$.ajax({
				url: "/erp/ajax/exam01",
				type: "get",
				data: querydata,	// 클라이언트에서 서버로 보내는 파라미터
				dataType: "json", // 응답되는 데이터의 종류
				success:function(data){	// data (변수명) - 서버와 통신하고 서버가 보내준 결과를 저장하는 변수
					//alert(data);
					//alert(data.title+", "+data.content);
					$("#no").text(data.board_no);
					$("#title").text(data.title);
					$("#writer").text(data.id);
					$("#content").text(data.content);
				},
				error: error_run
			})
		})
		// id가 testjsonbtn 인 버튼을 눌렀을 때 /erp/ajax/exam02/getjsondata 를 요청
		// -> json array 가 response 되면 id가 printarr인 div 에 내용을 출력할 수 있도록 정의
		
		$("#testjsonbtn").on("click", function(){
			$.ajax({
				url: "/erp/ajax/exam02/getjsondata",
				type: "get",
				datatype: "json",
				success: function(data){
					for (var el of data) {
						/* alert(el); */
						$("#printarr").append(el.board_no + ", " + el.title + ", " + el.id + ", " + el.content);
						$("#printarr").append("<br/>");
					}
				},
				error:error_run
			})
		})
		
	})	
	function error_run(obj, msg, statusMsg) {
		alert('error 발생 =>' + obj + ", " + msg + ", " + statusMsg);
	}
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
	<div id="printarr">
	
	</div>
</body>
</html>