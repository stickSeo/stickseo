<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="cardMethod" class="com.cardGame.CardMethod" />
<%
	 int numCount_a = 0;      // A번 배열 인덱스
	 int numCount_1 = 0;      // 1번 배열 인덱스
	 int numCount_2 = 0;      // 2번 배열 인덱스
	 int numCount_3 = 0;      // 3번 배열 인덱스
	 int numCount_b = 0;      // B번 배열 인덱스
	 
	 int[] a_shuffle = null;     // 섞인 A번 배열
	
	 // 초기화면
	 if(request.getParameter("mode")==null){        			
		 a_shuffle = cardMethod.shuffle();  					
	 	   for(int i=0; i<a_shuffle.length; i++){
		     session.setAttribute("cardArrayA_"+i, a_shuffle[i]);
		    }
	 // 게임 실행 화면
	 }else{                                                 
	
	     numCount_a = Integer.parseInt(request.getParameter("numCount_a"));
	     numCount_1 = Integer.parseInt(request.getParameter("numCount_1"));
	     numCount_2 = Integer.parseInt(request.getParameter("numCount_2"));
	     numCount_3 = Integer.parseInt(request.getParameter("numCount_3"));
	     numCount_b = Integer.parseInt(request.getParameter("numCount_b"));
	
		   // 넥스트일때
		  if(request.getParameter("mode").equals("nextClick")){
				numCount_a=numCount_a+1;
					if((Integer.parseInt(request.getParameter("numCount_a"))==9)){
					 	numCount_a = 0;
					}
		   }
		  
		  // a 배열->1 배열 
		  if(request.getParameter("mode").equals("numClick1")){
		  
			   if(numCount_1>0){
			   
				    if(Integer.parseInt(String.valueOf(session.getAttribute("cardArrayA_"+numCount_a)))
				    		< Integer.parseInt(String.valueOf(session.getAttribute("cardArray1_"+(numCount_1-1))))){
				    
				
				     session.setAttribute("cardArray1_"+numCount_1,String.valueOf(session.getAttribute("cardArrayA_"+numCount_a)));
				     session.removeAttribute("cardArrayA_"+numCount_a);
				        for(int i=numCount_a; i<10; i++){
				             int index = (i+1);
				                session.setAttribute("cardArrayA_"+i, String.valueOf(session.getAttribute("cardArrayA_"+index)));
				               
				          }
				         numCount_1=numCount_1+1;
				     }
				   
			   }else{
			    session.setAttribute("cardArray1_"+numCount_1,String.valueOf(session.getAttribute("cardArrayA_"+numCount_a)));
			    session.removeAttribute("cardArrayA_"+numCount_a);
			      for(int i=numCount_a; i<10; i++){
			           int index = (i+1);
			              session.setAttribute("cardArrayA_"+i, String.valueOf(session.getAttribute("cardArrayA_"+index)));
			             
			        }
			        numCount_1=numCount_1+1;
			    }
		 
		 }
		
		 // 1 배열->2 배열
		 if(request.getParameter("mode").equals("numClick2")){
		 
		   if(numCount_2>0){
		   
			    if(Integer.parseInt(String.valueOf(session.getAttribute("cardArray1_"+(numCount_1-1))))
			    		< Integer.parseInt(String.valueOf(session.getAttribute("cardArray2_"+(numCount_2-1))))){
			    
				     session.setAttribute("cardArray2_"+numCount_2,String.valueOf(session.getAttribute("cardArray1_"+(numCount_1-1))));
				     session.removeAttribute("cardArray1_"+(numCount_1-1));
						numCount_1=numCount_1-1;
						numCount_2=numCount_2+1;
			     }
		   
		   }else{
		    session.setAttribute("cardArray2_"+numCount_2,String.valueOf(session.getAttribute("cardArray1_"+(numCount_1-1))));
		    session.removeAttribute("cardArray1_"+(numCount_1-1));
				numCount_1=numCount_1-1;
				numCount_2=numCount_2+1;
		    }    
		 }
		
		  // 2 배열->3 배열
		 if(request.getParameter("mode").equals("numClick3")){
		 
		    if(numCount_3>0){
		
			     if(Integer.parseInt(String.valueOf(session.getAttribute("cardArray2_"+(numCount_2-1))))
			    		 < Integer.parseInt(String.valueOf(session.getAttribute("cardArray3_"+(numCount_3-1))))){
			     
			
			      session.setAttribute("cardArray3_"+numCount_3,String.valueOf(session.getAttribute("cardArray2_"+(numCount_2-1))));
			      session.removeAttribute("numCount_2"+(numCount_2-1));
					  numCount_2=numCount_2-1;
					  numCount_3=numCount_3+1;
			      }
		    
		    }else{
		     session.setAttribute("cardArray3_"+numCount_3,String.valueOf(session.getAttribute("cardArray2_"+(numCount_2-1))));
		     session.removeAttribute("cardArray2_"+(numCount_2-1));
		        numCount_2=numCount_2-1;
		        numCount_3=numCount_3+1;
		     }    
		 }
		
		 // 3 배열->b 배열
		 if(request.getParameter("mode").equals("numClickb")){
		 
		    if(numCount_b>0){
		
			     if(Integer.parseInt(String.valueOf(session.getAttribute("cardArray3_"+(numCount_3-1))))
			    		 > Integer.parseInt(String.valueOf(session.getAttribute("cardArrayB_"+(numCount_b-1))))){
			     
			
			      session.setAttribute("cardArrayB_"+numCount_b,String.valueOf(session.getAttribute("cardArray3_"+(numCount_3-1))));
			      session.removeAttribute("cardArray3_"+(numCount_3-1));
			        numCount_3=numCount_3-1;
			        numCount_b=numCount_b+1;
			      }
			    
		    }else{
		     session.setAttribute("cardArrayB_"+numCount_b,String.valueOf(session.getAttribute("cardArray3_"+(numCount_3-1))));
		     session.removeAttribute("cardArray3_"+(numCount_3-1));
				 numCount_3=numCount_3-1;
				 numCount_b=numCount_b+1;
		     }    
		
		 }
 
	 }
 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="gameCss.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
</head>
<body>
  <div>
	  <form name="cardFrm" id="cardFrm" method="post" action="">
		  <input type="hidden" name="numCount_a" id="numCount_a" value="">
		  <input type="hidden" name="numCount_1" id="numCount_1" value="">
		  <input type="hidden" name="numCount_2" id="numCount_2" value="">
		  <input type="hidden" name="numCount_3" id="numCount_3" value="">
		  <input type="hidden" name="numCount_b" id="numCount_b" value="">
		  <input type="hidden" name="mode" id="mode" value="">
		 
		   <%= numCount_a %>
		   <%= numCount_1 %>
		   <%= numCount_2 %>
		   <%= numCount_3 %>
		   <%= numCount_b %>
		  
		   <center> GAME </center>
		   <table cellspacing="20" cellpadding="20" border="1" align="center">
			    <tr>
				     <td style="background-color:green" align="center" valign=top> A </td>
				     <td style="background-color:red" align="center" align="center" valign=top> 1 </td>
				     <td style="background-color:red" align="center" align="center" valign=top> 2 </td>
				     <td style="background-color:red" align="center" align="center" valign=top> 3 </td>
				     <td style="background-color:green" align="center" align="center" valign=top> B </td>
			    </tr>
			    <tr>
				     <td>
				     <%
				     if(numCount_1<10){
					     if(session.getAttribute("cardArrayA_"+numCount_a).equals("null")){
					       numCount_a=0;
					      }
					       if(session.getAttribute("cardArrayA_"+numCount_a)!=null){
					        	if(!session.getAttribute("cardArrayA_"+numCount_a).equals("null")){
					      
					 %>
						      <input class="classname" type="button"
						       value="<%=session.getAttribute("cardArrayA_"+numCount_a)%>" 
						       	onclick="numClick1(<%=numCount_a%>,<%=numCount_1%>,<%=numCount_2%>,<%=numCount_3%>,<%=numCount_b%>)">
					 <%
					       		}
					       
					        }
				      }
				     %>
				     </td>
				     <td>
				     <%
				      for(int i=0; i<numCount_1; i++){
				        if(session.getAttribute("cardArray1_"+i)!=null){
				     %>
					      <input class="classname" type="button"
					       value="<%=session.getAttribute("cardArray1_"+i)%>" 
					          <%=(i==(numCount_1-1))?"":"disabled" %> 
					             onclick="numClick2(<%=numCount_a%>,<%=numCount_1%>,<%=numCount_2%>,<%=numCount_3%>,<%=numCount_b%>)"><br>
				     <%
				          }
				       }
				     %>
				     </td>
				     <td>
				     <%
				      for(int i=0; i<numCount_2; i++){
				        if(session.getAttribute("cardArray2_"+i)!=null){
				     %>
					      <input class="classname" type="button"
					       value="<%=session.getAttribute("cardArray2_"+i)%>" 
					          <%=(i==(numCount_2-1))?"":"disabled" %> 
					             onclick="numClick3(<%=numCount_a%>,<%=numCount_1%>,<%=numCount_2%>,<%=numCount_3%>,<%=numCount_b%>)"><br>
				     <%
				          }
				       }
				     %>
				     </td>
				     <td>
				     <%
				      for(int i=0; i<numCount_3; i++){
				        if(session.getAttribute("cardArray3_"+i)!=null){
				     %>
					     <input class="classname" type="button"
					       value="<%=session.getAttribute("cardArray3_"+i)%>" 
					       	<%=(i==(numCount_3-1))?"":"disabled" %> 
					       		onclick="numClickb(<%=numCount_a%>,<%=numCount_1%>,<%=numCount_2%>,<%=numCount_3%>,<%=numCount_b%>)"><br>
				     <%
				          }
				       }
				     %>
				     </td>
				     <td style="background-color:yellow">
				     <%
				      for(int i=0; i<numCount_b; i++){
					     if(session.getAttribute("cardArrayB_"+i)!=null){
					 %>
					      <input class="classname" type="button"
					       value="<%=session.getAttribute("cardArrayB_"+i)%>" disabled ><br>
					 <%
					       }
				        }
				     %>
				     </td>
			    </tr>
			    <tr>
				     <td>
				     		<input type="button" value="next" 
				     			onclick="nextClick(<%=numCount_a%>,<%=numCount_1%>,<%=numCount_2%>,<%=numCount_3%>,<%=numCount_b%>)">
				     </td>
			    </tr>
		   </table>
	 </form>
 </div>
 <script>
 
 // 넥스트 클릭시
 function nextClick(numCount_a,numCount_1,numCount_2,numCount_3,numCount_b){
 
	 $('#numCount_a').val(numCount_a);
	 $('#numCount_1').val(numCount_1);
	 $('#numCount_2').val(numCount_2);
	 $('#numCount_3').val(numCount_3);
	 $('#numCount_b').val(numCount_b);
	 $('#mode').val('nextClick');
	 $('#cardFrm').attr('action','game.jsp');
	 $('#cardFrm').submit();
 
 }
 // a 배열 클릭시
 function numClick1(numCount_a,numCount_1,numCount_2,numCount_3,numCount_b){
 
	 $('#numCount_a').val(numCount_a);
	 $('#numCount_1').val(numCount_1);
	 $('#numCount_2').val(numCount_2);
	 $('#numCount_3').val(numCount_3);
	 $('#numCount_b').val(numCount_b);
	 $('#mode').val('numClick1');
	 $('#cardFrm').attr('action','game.jsp');
	 $('#cardFrm').submit();
 
 }
 // 1 배열 클릭시
 function numClick2(numCount_a,numCount_1,numCount_2,numCount_3,numCount_b){
  
	 $('#numCount_a').val(numCount_a);
	 $('#numCount_1').val(numCount_1);
	 $('#numCount_2').val(numCount_2);
	 $('#numCount_3').val(numCount_3);
	 $('#numCount_b').val(numCount_b);
	 $('#mode').val('numClick2');
	 $('#cardFrm').attr('action','game.jsp');
	 $('#cardFrm').submit();
	  
 }
 // 2 배열 클릭시
 function numClick3(numCount_a,numCount_1,numCount_2,numCount_3,numCount_b){
  
	 $('#numCount_a').val(numCount_a);
	 $('#numCount_1').val(numCount_1);
	 $('#numCount_2').val(numCount_2);
	 $('#numCount_3').val(numCount_3);
	 $('#numCount_b').val(numCount_b);
	 $('#mode').val('numClick3');
	 $('#cardFrm').attr('action','game.jsp');
	 $('#cardFrm').submit();
  
 }
 // 3 배열 클릭시
 function numClickb(numCount_a,numCount_1,numCount_2,numCount_3,numCount_b){
  
	 $('#numCount_a').val(numCount_a);
	 $('#numCount_1').val(numCount_1);
	 $('#numCount_2').val(numCount_2);
	 $('#numCount_3').val(numCount_3);
	 $('#numCount_b').val(numCount_b);
	 $('#mode').val('numClickb');
	 $('#cardFrm').attr('action','game.jsp');
	 $('#cardFrm').submit();
 
 }
 
 </script>
</body>
</html>