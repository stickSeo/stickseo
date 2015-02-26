<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>다중 파일 업로드</h1>
<form name="fileForm" id="fileForm" method="post" enctype="multipart/form-data" action="fileSelect.jsp">
<table border="1">

	<tr>
		<td>user</td>
		<td><input type="text" name="user"></td>
		<td>title</td>
		<td><input type="text" name="title"></td>
	</tr>
	<tr>
		<td>
		content
		</td>
		<td colspan="3">
		<textarea name="content" rows="3" cols="50"></textarea>
		</td>
	
	</tr>
	<tr>
		<td colspan="4">
		추가할 파일 수 입력 <input type="text" id="addcnt" name="addcnt">
		<input type="button" id="addbtn" value="확인">
		</td>
	</tr>
</table>
<br>
<table border="1">
	<tr>
		<td  style="width:400px" id="fileTd">
		
		</td>
		<td>
			<input type="button" value="done" onclick="goUpload(this.form)">
		</td>
	</tr>

</table>
</form>
<script  src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script>

	 
	 function goUpload(formName){
		 
		 formName.action = 'fileSelect.jsp';
		 formName.submit();
	 }
	 
	// 다중 파일 처리
	// 파일 다중처리시에는 파일마다 이름값을 다르게 해주어야 다수의 값들을 받을 수 있다.
	 $('#addbtn').click(function(event){
			
			var idx = 1;
			var addcnt = $('#addcnt').val();

			for(idx; idx<=addcnt; idx++){
	 			var html = "<input type='file' id='file"+idx+"' name='file"+idx+"'>";
				$('#fileTd').append(html);
			}

	 });
	 

</script>
</body>

</html>