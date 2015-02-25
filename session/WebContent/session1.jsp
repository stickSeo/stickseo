<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<% 
	request.setCharacterEncoding("UTF-8");	
	String id = request.getParameter("id");
	String password = request.getParameter("pw");
	
	session.setAttribute("idkey", id);
	session.setMaxInactiveInterval(200);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="session1_1.jsp">

	1. 좋아하는 계절은? 
	<br>
	<input type="radio" name="season" value="봄">봄
	<input type="radio" name="season" value="여름">여름
	<input type="radio" name="season" value="가을">가을
	<input type="radio" name="season" value="겨울">겨울
	<br>
	
	1. 좋아하는 과일은? 
	<br>
	<input type="radio" name="fruit" value="사과">사과
	<input type="radio" name="fruit" value="딸기">딸기
	<input type="radio" name="fruit" value="오렌지">오렌지
	<input type="radio" name="fruit" value="수박">수박
	<br>
	
	<input type="submit" value="전송">
	
</form>
</body>
</html>