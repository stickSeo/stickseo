<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	long totalMoney =0;
	long totalTime = 0;
	int newCarNum = 0;
	String mode = "main";
	int carTotalNum = 0;

	if (request.getParameter("newCarNum") != null) {
		newCarNum = Integer.parseInt(request.getParameter("newCarNum"));
	}
	if (request.getParameter("totalNum") != null) {
		carTotalNum = Integer.parseInt(request.getParameter("totalNum"));
	}
	if(session.getAttribute("totalTime")!=null){
		totalTime = Long.valueOf(String.valueOf(session.getAttribute("totalTime")));
	}
	if(session.getAttribute("totalMoney")!=null){
		totalMoney = Long.valueOf(String.valueOf(session.getAttribute("totalMoney")));
	}
	if (request.getParameter("mode") != null) {

		mode = request.getParameter("mode");

		if (mode.equals("inClick")) {

			session.setAttribute("carImage"+newCarNum+"", "images.jpg");
			session.setAttribute("carTIme"+newCarNum+"", System.currentTimeMillis());
			
			for (int i = 1; i < 6; i++) {
				if (session.getAttribute("carImage" + i) == null) {
					newCarNum = i - 1;
					break;
				}
			}

		} else if (mode.equals("outClick")) {

			totalTime = totalTime+ (System.currentTimeMillis() - Long.valueOf(String.valueOf(session.getAttribute("carTIme" + newCarNum)))) / 1000;
			totalMoney = totalMoney + (System.currentTimeMillis() - Long.valueOf(String.valueOf(session.getAttribute("carTIme" + newCarNum)))) / 1000 * 100;
			session.setAttribute("totalTime", totalTime);
			session.setAttribute("totalMoney", totalMoney);
			session.removeAttribute("carImage" + newCarNum);
			for (int i = 1; i < 6; i++) {
				if (session.getAttribute("carImage" + i) == null) {
					newCarNum = i - 1;
					break;
				}
			}
		}
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
</head>
<body>
	<div>
	    <center>TOTAL</center>
	    <input type="button" name="reflesh" id="reflesh" value="새로고침" onclick="javascript:reload()">
		<table border="1" width="40%" height="10px" align="center">
			<tr>
				<td>시간<input type="text" name="totalTime" id="totalTime" value="<%=totalTime%>"></td>
				<td>요금<input type="text" name="totalMoney" id="totalMoney"value="<%=totalMoney%>"></td>
			</tr>
		</table>
		<br>
		<table border="1px" width="40%" height="40%" align="center">
			<%
				int totalNum = 0;
				for (int index = 1; index < 6; index++) {
			%>
			<tr>
				<td rowspan="2">
					<%
						if (session.getAttribute("carImage" + index) != null) {
								totalNum++;
					%> 
							<input type="button" name="outBtn" id="outBtn<%=index%>"value="Out" onclick="outClick(<%=index%>)"> 
					<%
 						}
 					%>

				</td>

				<td rowspan="2">
					<%
						if (session.getAttribute("carImage" + index) != null) {
					%> 		<img alt="" name="carImage<%=index%>" id="carImage<%=index%>" src="<%=session.getAttribute("carImage" + index)%>"> 
					<%
 						}
 					%>
				</td>
				<td width="30%">
					<%
						if (session.getAttribute("carImage" + index) != null) {
					%> 
							시간
							<input type="text" id="carTIme<%=index%>" 
								value="<%=(System.currentTimeMillis() - Long.valueOf(String.valueOf(session.getAttribute("carTIme" + index)))) / 1000%>">
					<%
						}
					%>
				</td>
			</tr>

			<tr>
				<td>
					<%
						if (session.getAttribute("carImage" + index) != null) {
					%> 
							요금
							<input type="text" id="carTIme<%=index%>" 
								value="<%=(System.currentTimeMillis() - Long.valueOf(String.valueOf(session.getAttribute("carTIme" + index)))) / 1000 * 100%>">
					<%
						}
					%>
				</td>
			</tr>
			<%
				}
			%>
		</table>

	</div>

	<div style="margin: 0 auto; align =: center">
		<form name="carForm" id="carForm" method="post" action="">
			<input type="hidden" name="newCarNum" id="newCarNum" value="">
			<input type="hidden" name="totalNum" id="totalNum" value="">
			<input type="hidden" name="mode" id="mode" value="">
			<table>
				<tr>
					<td>
						<input type="button" name="inButton" id="inButton" size="50" value="IN" onclick="javascript:inClick(<%=newCarNum%>)">
					</td>
					<td>
						<img alt="" src="images.jpg">
					</td>
				</tr>
			</table>
		</form>
	</div>

	<script>

		var totalNum = <%=carTotalNum%>;

		// 차 추가
		function inClick(newCarNum){
			
	 		totalNum = totalNum+1;
			$('#newCarNum').val(newCarNum+1);
			$('#mode').val("inClick");
			$('#totalNum').val(totalNum);
			$('#carForm').attr('action','index.jsp');
			$('#carForm').submit();
		
		}
		
		// 만차 
		if(<%=carTotalNum%> > 5){
		
		  	alert("만차");
			$('#newCarNum').val(<%=newCarNum%>-1);
			$('#totalNum').val(<%=carTotalNum%>-1);
			$('#carForm').attr('action','index.jsp');
			$('#carForm').submit();
		
		}
		
		// 차 삭제
		function outClick(index){
		
	 		totalNum = totalNum-1;
 			$('#newCarNum').val(index);
 			$('#mode').val("outClick");
			$('#totalNum').val(totalNum);
 			$('#carForm').attr('action','index.jsp');
 			$('#carForm').submit();
		}
		
	</script>

</body>
</html>