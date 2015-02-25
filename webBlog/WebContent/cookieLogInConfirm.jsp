<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import = "java.util.*, ch10.*" %>
<jsp:useBean id="memMgr" class="ch10.RegisterMgrPool" scope="page"/>

<% 
    request.setCharacterEncoding("UTF-8");

	String memberId = "";
	Cookie[] cookies = request.getCookies();
	if(cookies!=null){
	   for(int i=0; i<cookies.length; i++){
          if(cookies[i].getName().equals("ID")){
			memberId = cookies[i].getValue();
		  }
	   }
	   if(memberId.equals("")){
%> 
	<script>
	  alert("로그인 되지 않았습니다.");
	     location.href="cookieMemberLogIn.jsp";
	</script>
<%	   }	
	}
%>
<html>
<head>
<title>Simple LogIn</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="#996600" topmargin="100">
<table width="75%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#660000" bgcolor="#FFFF99">
  <tr bordercolor="#FFFF99"> 
    <td height="190" colspan="7"> 
      <table width="50%" border="1" align="center" cellspacing="0" cellpadding="0">
        <tr bordercolor="#FFFF66"> 
          <td colspan="2"><div align="center">Log On Page</div></td>
        </tr>
        <tr >         
          <td><div align="center"> 
           <strong><%= memberId %></strong>
			님이 로그인 하셨습니다. 
            </div></td>
		  <td><div align="center"> 
           <a href="cookieLogOut.jsp"><strong>Log Out</div></a></td>
        </tr>
      </table>
     </td>
  </tr>
</table>
<%@include file="usigeJDBC_bean.jsp"%>
</body>
</html>
