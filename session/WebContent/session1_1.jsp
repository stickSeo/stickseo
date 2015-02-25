<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<% 
	request.setCharacterEncoding("UTF-8");	
	String id = (String)session.getAttribute("idkey");
	String season = request.getParameter("season");
	String fruit = request.getParameter("fruit");
	String sessionId = session.getId();
	int intervalTime = session.getMaxInactiveInterval();

%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	if(id!=null){
%>
		<b><%=id %></b>님이 좋아하는 계절과 과일은 <br>
		<b><%=season %></b>와 <b><%=fruit %></b>입니다.<br>
		<hr>
		세션 아이디 : <%=sessionId %><br>
		세션 유지시간 : <%=intervalTime %>초입니다.<br>

<%
	session.invalidate();
	}else{
		out.println("세션의 시간이 경과를 하였거나 다른 이유로 연결을 할 수가 없습니다.");
	}
%>
</body>
</html>