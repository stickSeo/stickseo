<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <jsp:useBean id="test" class="ch08.SimpleBean" scope="page"/>
 <jsp:setProperty name="test" property="message" value="빈을 쉽게 정복하자"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
Message : <jsp:getProperty name="test" property="message"/>
</body>
</html>