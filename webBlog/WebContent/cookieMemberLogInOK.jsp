<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<jsp:useBean id="memMgr" class="ch10.RegisterMgrPool" />
<%
    String memberId = "";
	String memberPw = "";
	if(request.getParameter("id") != null)  
	   memberId  = request.getParameter("id");
	if(request.getParameter("pw") != null) 
	   memberPw  = request.getParameter("pw");
	if(memMgr.passCheck(memberId, memberPw)){
		Cookie cookie = new Cookie("ID", memberId);
		response.addCookie(cookie);
%>
	<script> 
	  alert("로그인 되었습니다.");
      location.href="cookieLogInConfirm.jsp";
	</script>
<%	}else{ %>
	<script>
	  alert("로그인 되지 않았습니다.");
      location.href="cookieMemberLogIn.jsp";
	</script>
<%	}	%>