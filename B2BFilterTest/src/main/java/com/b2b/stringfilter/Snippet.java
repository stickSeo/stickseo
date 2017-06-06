package com.b2b.stringfilter;


public class Snippet {
	/** 
	    * xss 공격과 sql injection 을 필터링 한다. 
	    * @param source : 필터링 될 문자열 
	    * @param kind : 해킹 분류 ("xss" , "sqlIj", "예외키워드1", "예외키워드2", ... , "예외키워드n") 
	    * @return 필터링 된 문자열 
	    */ 
	    public static String cleanXssSqlIj(String source, String kind, String... except) { 
	        if(source == null || source.equals("")){ 
	          return ""; 
	        } 
	        String[][] _keyword = { 
	            {"script","function","object","applet","embed","alert","href","cookie","fromcharcode","encodeuri","encodeuricomponent","expression","window","style"}, 
	            {"onclick","ondblclick","onmousedown","onmousemove","onmouseout","onmouseup","onmouseover","onmouseleave","onkeydown","onkeypress","onkeyup","onblur","onchange","onfocus","onreset","onselect","onsubmit","onload","onresize","onunload"}, 
	            {"having","from","drop","where","union","substr","declare","openrowset","user_tables","user_tab_columns","table_name","column_name","row_num"} 
	        }; 
	        String _key01 = null; 
	        String _key02 = null; 
	        String _key03 = null; 
	        java.util.List<String> _xss_a = null; 
	        java.util.List<String> _xss_b = null; 
	        java.util.List<String> _sqlIj_a = null; 
	         
	        if(except.length > 0){ 
	          _xss_a = new java.util.ArrayList<String>(); 
	          _xss_b = new java.util.ArrayList<String>(); 
	          _sqlIj_a = new java.util.ArrayList<String>(); 
	           
	          java.util.Collections.addAll(_xss_a, _keyword[0]); 
	          java.util.Collections.addAll(_xss_b, _keyword[1]); 
	          java.util.Collections.addAll(_sqlIj_a, _keyword[2]); 
	           
	          for(int i = 0; except.length > i; i++){ 
	            _xss_a.remove(except[i]); 
	            _xss_b.remove(except[i]); 
	            _sqlIj_a.remove(except[i]); 
	          } 
	           
	          _key01 = "(?i)("+join(_xss_a,"|")+")"; 
	          _key02 = "(?i)("+join(_xss_b,"|")+")"; 
	          _key03 = "(?i)("+join(_sqlIj_a,"|")+")"; 
	        }else{ 
	          _key01 = "(?i)("+join(_keyword[0],"|")+")"; 
	          _key02 = "(?i)("+join(_keyword[1],"|")+")"; 
	          _key03 = "(?i)("+join(_keyword[2],"|")+")"; 
	        } 
	         
	        try{ 
	        	
	          source = source.replaceAll("(?i)(\\&\\#x?([0-9]|[a-f])*;)*", ""); 
	          System.out.println(" %25 으로 바꾸기  전 source " + source);
	          source = source.replaceAll("%(?![0-9a-fA-F]{2})", "%"); 
	          System.out.println(" 디코드 전 source " + source);
	          // + 문자 공백처리되는 문제
	          source = java.net.URLDecoder.decode(source, "UTF-8"); 
	          System.out.println(" 디코드 후 source " + source);
	          source = source.replaceAll("(?i)([^e]x{1}([0-9]|[a-f]){2,})*", ""); 
	          //source = java.util.regex.Pattern.compile("(?i)(\\({1}.*\\){1})", java.util.regex.Pattern.DOTALL).matcher(source).replaceAll(""); 
	        }catch(Exception e){ 
	          e.printStackTrace(); 
	        } 
	
	        if ("xss".equals(kind)) { 
	          source = source.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>","").replaceAll("\r|\n|&nbsp;",""); 
	          source = source.replaceAll("('|\")*/*>+","").replaceAll("<+('|\")*/*",""); 
	       
	          int _len = source.length()/3; 
	       
	          for(int i = 0; i < _len; i++){ 
	            source = source.replaceAll(_key01,""); 
	            source = source.replaceAll(_key02,""); 
	          } 
	        }else if("sqlIj".equals(kind)) { 
	          source = source.replaceAll(_key03,""); 
	        } 
	
	        return source; 
	      } 
	       
	      public static String join(java.util.Collection collection, String separator){ 
	          if(collection == null) 
	              return null; 
	          else 
	              return join(collection.iterator(), separator); 
	      } 
	
	      public static String join(java.util.Iterator iterator, String separator){ 
	          if(iterator == null) 
	              return null; 
	          if(!iterator.hasNext()) 
	              return ""; 
	          Object first = iterator.next(); 
	          if(!iterator.hasNext()) 
	            return first != null ? first.toString() : ""; 
	          StringBuffer buf = new StringBuffer(256); 
	          if(first != null) 
	              buf.append(first); 
	          do { 
	              if(!iterator.hasNext()) 
	                  break; 
	              if(separator != null) 
	                  buf.append(separator); 
	              Object obj = iterator.next(); 
	              if(obj != null) 
	                  buf.append(obj); 
	          } while(true); 
	          return buf.toString(); 
	      } 
	
	      public static String join(Object array[], String separator){ 
	          if(array == null) 
	              return null; 
	          if(separator == null) 
	              separator = ""; 
	          int bufSize = array.length - 0; 
	          if(bufSize <= 0) 
	              return ""; 
	          bufSize *= (array[0] != null ? array[0].toString().length() : 16) + separator.length(); 
	          StringBuffer buf = new StringBuffer(bufSize); 
	          for(int i = 0; i < array.length; i++) { 
	              if(i > 0) 
	                  buf.append(separator); 
	              if(array[i] != null) 
	                  buf.append(array[i]); 
	          } 
	
	          return buf.toString(); 
	      } 
	
}

