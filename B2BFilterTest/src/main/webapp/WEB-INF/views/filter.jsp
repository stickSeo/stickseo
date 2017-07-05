<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page import="com.b2b.urlcheck.UrlCheck"%>
<!-- 페이지 url 프로퍼티 클래스 -->
<%@ page import="com.b2b.pagecomponent.*" %>
<jsp:include page="./bootstrap/style.jsp"></jsp:include>
<% 
	UrlCheck urlChk = new UrlCheck();
	String locationScript = urlChk.getHttpsUrl(request);

%>
<html>
<head>
<style type="text/css">

input {
	width : 100%;
}
a {

	background-color: #EEEEEE;
	margin-left : 50%;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
</head>
<body>
	<h1>
		******************* Filter TEST *******************  
	</h1>
		<a href = "<%=PageComponent.boaldList%>"> 게시판 보기 </a>
		
		<div>
			<form id="form" method="get" action="<%=PageComponent.filterProc %>" >
			
				id  <input type="text" name="id" /> <br/>
				pwd  <input type="text" name="pwd" /> <br/>
				msg  <input type="text" name="msg" value="" /> <br/>
				script_msg  <input type="text" id="s_msg" value=""/> <br/>
				<br />
				<input type="submit" id="goFilterTest" value="submit" />
	
			</form>
		</div>
		<br/> <hr> <br/>
		<div>
			Filter_ id <input type="text" value="${id}"/> <br/>
			Filter_ pwd  <input type="text" value="${pwd}"/> <br/>
			Filter_ msg  <input type="text" value="${msg}"/> <br/>
		</div>
	
		<script src="//code.jquery.com/jquery.min.js"></script>
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
		<script>
		
		$(window).load(function() {
		    
			// ssl 처리
			<%=locationScript%>
			
			// 새로고침 
			document.onkeydown = function(e){
				if(e.keyCode == 116)
				{
			 		window.onbeforeunload = null;
				}
				else if (e.which === 82 && e.ctrlKey) {
			 		window.onbeforeunload = null;
		        }
				if(e.keyCode==93) { alert('메뉴키는 사용 불가능합니다.'); return false; }
				if(e.keyCode==41) { alert('메뉴키는 사용 불가능합니다.'); return false; }
			}
			
			//오른쪽마우스 막기
			document.oncontextmenu = function(e){
			 if(e){
			  e.preventDefault();
			 }
			 else{
			  event.keyCode = 0;
			  event.returnValue = false;
			 }
			}

			function exitAlert()
		 	{
			    if(confirm('Are you sure you want to leave the current page?')) {
// 			      return true;
			    } 
			    else 
			    {
			      if(window.event) {
			        window.event.returnValue = false;
			      } else {
			        e.preventDefault();
			      }
// 			      return false;
			    }
		 	}
			 
			 function test()
			 {
				var pop =  window.open('<%=PageComponent.logOutConfirm_pop%>',
						'target_name','scrollbars=yes,toolbar=yes,resizable=yes,width=300,height=200,left=600,top=200');
			 	pop.focus();
			 }
			 
		 	 // window.onbeforeunload = exitAlert;
		
			
		 	 $('form').submit(function(){
		 		  window.onbeforeunload = null;
		 	 });
		 	 
			// 2016.09.26 자바스크립트 함수 업데이트
			// javascript 필터 함수
			function cleanXss(src) {
				 if (!src) {
				  return "";
				 }
				var src = decodeURIComponent(src);
				var validArray = ["script","function","object","applet","embed",
				                  "alert","href","cookie","fromcharcode","encodeuri",
				                  "encodeuricomponent","expression","window","style"];
				
				for( var j=1; j<=arguments.length-1;)
				{	
					var index = validArray.indexOf(arguments[j]);
					if( index!= -1 )
						{
							validArray.splice(index, 1);
							j++;
						}
				}
				src = src.replace(/<(\/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(\/)?>/gi,"").replace(/\r|\n|&nbsp;/gi,"");
				src = src.replace(/('|\")*\/*>+/gi,"").replace(/<+('|\")*\/*/gi,"");
				
				for( var i=0; i <= validArray.length-1; i++ )
 				{
					src = src.replace(new RegExp(validArray[i], 'gi'), "");
				}

				return src;
			}
			
			
				
			$("#goFilterTest").click( function(){
			
				var filterTest = $("#s_msg").val().trim();
				if( ""!=( filterTest ) )
				{
					alert( " String : " + filterTest );
					//제외할 문자가 있는경우
					alert( " Filter String 1 : " + cleanXss( filterTest , "script", "object") );
					//제외할 문자가 없는경우
					alert( " Filter String 2 : " + cleanXss( filterTest ) );

				}
				$("#form").submit();
			});
			
		});
		
		</script>
	</body>
</html>
