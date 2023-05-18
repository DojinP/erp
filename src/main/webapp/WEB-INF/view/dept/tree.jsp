<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	
	<link rel="stylesheet" href="/erp/common/css/jquery.treeview.css" />
	<link rel="stylesheet" href="/erp/common/css/screen.css" />
	<script src="/erp/common/js/jquery.cookie.js"></script>
	<script src="/erp/common/js/jquery.treeview.js"></script>
	<script type="text/javascript" src="/erp/common/js/demo.js"></script>
</head>
<body>
	<h4>조직도</h4>
	<ul id="browser" class="filetree">
		<c:forEach var="dept" items="${deptlist}">
			<c:if test="${dept.deptlevel==1}">
				<li class="closed">
					<span class="folder">${dept.deptname}</span>
					<ul id="${dept.deptno}">
						<c:if test="${dept.deptlevel==2}">
							<li><span class="folder">File 2.1.1</span></li>
							<li><span class="file">File 2.1.1</span></li>
						</c:if>
					</ul>
				</li>
			</c:if>
		</c:forEach>
		
		
		<!-- <li><span class="folder">Folder 1</span>
			<ul>
				<li><span class="file">Item 1.1</span></li>
			</ul>
		</li>
		<li><span class="folder">Folder 2</span>
			<ul>
				<li><span class="folder">Subfolder 2.1</span>
					<ul id="folder21">
						<li><span class="file">File 2.1.1</span></li>
						<li><span class="file">File 2.1.2</span></li>
					</ul>
				</li>
				<li><span class="file">File 2.2</span></li>
			</ul>
		</li>
		<li class="closed"><span class="folder">Folder 3 (closed at start)</span>
			<ul>
				<li><span class="file">File 3.1</span></li>
			</ul>
		</li>
		<li><span class="file">File 4</span></li> -->
	</ul>
</body>
</html>