<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>
<!-- 페이지 url 프로퍼티 클래스 -->
<%@ page import="com.b2b.pagecomponent.*" %>
<%@ page import="com.b2b.dto.BoardDTO" %>
<%@ page import = "java.util.Map, java.util.List"%>
<%
	BoardDTO boardDto = new BoardDTO();
	Map<String,Object> total_Cnt = (Map<String,Object>)request.getAttribute("total_cnt");
	int pageCnt = Integer.parseInt( String.valueOf( total_Cnt.get("CNT") ) );
	
	if( (pageCnt%boardDto.getRowNum())!=0)
	{
		pageCnt = pageCnt/boardDto.getRowNum();
		pageCnt = pageCnt + 1;
	}else
	{
		pageCnt = pageCnt/boardDto.getRowNum();
	}
	if (pageCnt==0) pageCnt =1;
	
	List<Map<String,Object>>list = (List<Map<String,Object>>)request.getAttribute("list");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<style>
			.board th, .board td 
			{ 
				border:1px solid #ccc; 
				width : 100px;
			}
			input[type="text"]
			{
				width : 400px;
			}
			a 
			{
				background-color: #EEEEEE;
				margin-left : 30%;
			}	
		</style>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	</head>
	<body>
	<center>
			<h2>게시판 목록</h2>
			<a href = "<%=PageComponent.filter%>"> 필터 테스트 </a>
			<form id="searchfrm" action="">
				<input type="text" id="searchKey" name="searchKey" />
				<input type="button" id="searchBtn"  value="search"/>
			</form>
			<table class="board">
			    <colgroup>
			        <col width="20px"/>
			        <col width="100px"/>
			        <col width="30px"/>
				    <col width="20px"/>
			        <col width="30px"/>
			    </colgroup>
			    <thead>
			        <tr>
			            <th scope="col">글번호</th>
			            <th scope="col">제목</th>
			            <th scope="col">조회수</th>
			            <th scope="col">작성자</th>
			            <th scope="col">작성일</th>
			        </tr>
			    </thead>
			    <tbody>
						<%
							if ( list.size() > 0 )
							{
								for ( Map<String, Object> data : list )
								{
						%>   
									<tr>
										<td><%=data.get("B_NO") %></td>
										<td><%=data.get("B_TITLE") %></td>
										<td><%=data.get("B_VEIWCNT") %></td>
										<td><%=data.get("B_NAME") %></td>
										<td><%=data.get("B_DATE") %></td>
									</tr> 
						<%
								}
							} 
							else
							{
						%> 
								<tr> <td colspan="5"> 게시물이 존재하지 않습니다. </td> </tr> 
						<%
							}
						%>
			    </tbody>
			</table>
		
			<table class="pasing">
				<tr>
				<td> <a href="javascript:;" onclick=""> < </a></td>
			<%
				for(int i=1; i<=pageCnt; i++)
				{
			%>	
					<td><a href="/sample/boardList?startPnum=<%=i %>" ><%=i %></a></td>
			<%
				}
			%>  
				<td> <a href="javascript:;" onclick=""> > </a></td>
				</tr>
			</table>
		</center>
		<script src="//code.jquery.com/jquery.min.js"></script>
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
		<script>
		
		$(document).ready(function() {
			function pasing(yap)
			{
				var test = yap;
				alert(test);
			}
		});
		
		$(window).load(function() {
			
			
			function search()
			{
				if(e.keyCode == 13 )
				{
					if ( $('#searchKey').val() == '' )
					{
						alert(' 검색어를 입력하세요. ')
						$('#searchKey').focus();
						return;
					}
					$('#searchfrm').attr('action', '/sample/search');
					$('#searchfrm').submit();
				}
			}
			
			$('#searchKey').keydown( function(e){
				
				if(e.keyCode == 13 )
				{
					if ( $('#searchKey').val() == '' )
					{
						alert(' 검색어를 입력하세요. ')
						$('#searchKey').focus();
						return;
					}
					$('#searchfrm').attr('action', '/sample/search');
					$('#searchfrm').submit();
				}
				
			});
			
			$('#searchBtn').click( function() {
				if ( $('#searchKey').val() == '' )
				{
					alert(' 검색어를 입력하세요. ')
					$('#searchKey').focus();
					return;
				}
				$('#searchfrm').attr('action', '/sample/search');
				$('#searchfrm').submit();
			});
			
		
		});
		</script>
	</body>
</html>