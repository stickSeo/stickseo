<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%
	String cookieName = "";
	String memberId = "";
	Cookie[] cookies = request.getCookies();
	if(cookies!=null){
		for(int i=0; i<cookies.length; i++){
			if(cookies[i].getName().equals("ID")){
				cookieName = cookies[i].getName();
				memberId = cookies[i].getValue();
			}
		}
		if(!memberId.equals("")){
%>
	<script> 
	  alert("로그인 되었습니다.");
      location.href="cookieLogInConfirm.jsp";
	</script>
<%		}
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
	   <form method="post" action="cookieMemberLogInOK.jsp">
        <table width="50%" border="1" align="center" cellspacing="0" cellpadding="0">
          <tr bordercolor="#FFFF66"> 
            <td colspan="2"><div align="center">Log in</div></td>
          </tr>
          <tr > 
            <td width="47%"><div align="center">ID</div></td>
            <td width="53%"><div align="center"> 
                <input type="text" name="id">
              </div></td>
          </tr>
          <tr> 
            <td><div align="center">Password</div></td>
            <td><div align="center"> 
                <input type="password" name="pw">
              </div></td>
          </tr>
          <tr> 
            <td colspan="2"><div align="center"> 
                <input type="submit" value="login">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                <input type="reset" value="reset">
              </div></td>
          </tr>
        </table>
      </form></td>
  </tr>
</table>
</body>
</html>