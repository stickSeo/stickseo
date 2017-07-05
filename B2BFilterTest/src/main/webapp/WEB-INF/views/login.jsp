<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page import="com.b2b.urlcheck.UrlCheck"%>
<!-- 페이지 url 프로퍼티 클래스 -->
<%@ page import="com.b2b.pagecomponent.*" %>
<jsp:include page="./bootstrap/style.jsp"></jsp:include>
<html>
<%
	int[] red = {1,2,3,4,5};
	int[] brue = {1,2,3,4,5};
	
	int ball = (int)(Math.random()*5)+1;
	
%>
	<head>
	    <script src="//code.jquery.com/jquery.min.js"></script>
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
		<script>
		$(window).load(function() {
			

	 	    
		});
		</script>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	</head>
	<body>
	  <br>
	  <div align="center">
	    <h1> Login </h1>
		<form class="form-inline" id="form" method="post" action="<%=PageComponent.loginProc %>" >
		<div class="form-group">
		    <label for="id">아이디 </label>
		    <input type="text" class="form-control" name="id" placeholder="아이디를 입력하세요">
		</div>
		<div class="form-group">
		    <label for="password">암호</label>
		    <input type="password" class="form-control" name="pwd" placeholder="암호">
		</div>
		  <button type="button" id="goLogin" class="btn btn-default">로그인</button>
		</form>
		<div id="safetyDiv">
		<div class="checkbox" >
		    <label>
		      <input type="checkbox" id="safetyCheck" value="true"> 보안 인증
		    </label>
		</div>
		</div>
	 </div>
	 <form>
  
</form>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
	<script src="//code.jquery.com/jquery.min.js"></script>
	<script>
	 	$("#goLogin").click( function(){
	 		
	 		if(!$("#safetyCheck").prop( "checked")){
	 			alert('보안인증 해주세요.');
	 		}else{
	 			$("#form").submit();
	 		}
	 	});
	 	$("#safetyCheck").click( function(){
	 		$("#safetyDiv").hide();
	 	});
	</script>
	</body>
</html>