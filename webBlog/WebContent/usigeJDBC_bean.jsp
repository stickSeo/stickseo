<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import = "java.util.*, ch10.*" %>
<%-- <jsp:useBean id="memMgr" class="ch10.RegisterMgrPool" scope="page"/> --%>
<%
	
	ArrayList data = memMgr.getMemberList();

%>
<!-- <!DOCTYPE html> -->
<!-- <html> -->
<!-- <head> -->
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
<!-- <title>Insert title here</title> -->
<!-- </head> -->
<!-- <body> -->
    <center>
	<h1>JDBC,JAVABeans 와 연동한 테이블 실습</h1>
	<hr>
	</center>
	<table bordercolor="#0000ff" border="1" align="center">
		<tr>
		  <td><strong>ID</strong></td>
		  <td><strong>PASSWD</strong></td>
		  <td><strong>NAME</strong></td>
		  <td><strong>NUM1</strong></td>
		  <td><strong>NUM2</strong></td>
		  <td><strong>EMAIL</strong></td>
		  <td><strong>PHONE</strong></td>
		  <td><strong>ZIPCODE</strong></td>
		  <td><strong>JOB</strong></td>
		
		</tr>
	<%
	
		if(data.size()>0){
			for(int i=0; i<data.size(); i++){
				
				RegisterBean regBean  = (RegisterBean)data.get(i);
	%>
		<tr>
		  <td><strong><%=regBean.getId() %></strong></td>
		  <td><strong><%=regBean.getPasswd() %></strong></td>
		  <td><strong><%=regBean.getName() %></strong></td>
		  <td><strong><%=regBean.getNum1() %></strong></td>
		  <td><strong><%=regBean.getNum2() %></strong></td>
		  <td><strong><%=regBean.getEmail() %></strong></td>
		  <td><strong><%=regBean.getPhone() %></strong></td>
		  <td><strong><%=regBean.getZipcode() %></strong></td>
		  <td><strong><%=regBean.getJob() %></strong></td>
		</tr>
	<%
			}
		}else{	
	%>	
		<tr>
			<td colspan="9"> 데이터가 존재 하지 않습니다. </td>
		</tr>
	<%
		}
	%>
	</table>
<!-- </body> -->
<!-- </html> -->