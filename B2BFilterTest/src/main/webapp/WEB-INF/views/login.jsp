<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page import="com.b2b.urlcheck.UrlCheck"%>
<!-- 페이지 url 프로퍼티 클래스 -->
<%@ page import="com.b2b.pagecomponent.*" %>
<html>
<head>
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
		<form id="form" method="post" action="<%=PageComponent.filter %>" >
		
			id  <input type="text" name="id" /> <br/>
			pwd  <input type="password" name="pwd" /> <br/>
			<br />
			<!-- <input type="submit" id="goLogin" value="Login" />  -->
			<input type="button" id="goLogin" value="Login" />
		</form>
	 </div>
	 
	 <script src="//code.jquery.com/jquery.min.js"></script>
	 <script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
	 <script>
	 	$("#goLogin").click( function(){
	 		$("#form").submit();
	 	});
	 </script>
	</body>
</html>