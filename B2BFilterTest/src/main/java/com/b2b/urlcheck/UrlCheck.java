package com.b2b.urlcheck;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;

public class UrlCheck {
	
	// 크로스 사이트 요청위조 방지
	public boolean refereCheck (HttpServletRequest request)
	{
		// null 체크 하지 않을 url 리스트
		String[] null_n_Url = {"http://localhost:8080/","http://localhost:8080/login"};
		String	referer = request.getHeader("referer");
		boolean check = false;
		
		try 
		{
			// 현재 url
			String currentUrl = request.getScheme() + "://" + request.getServerName();
				   currentUrl += (request.getServerPort()==80?"":":"+request.getServerPort()); 
				   currentUrl += request.getRequestURI();
				   
			String host = request.getServerName();
			
			System.out.println(" currentUrl >>>>>>>>>>>>>> " + currentUrl);
			System.out.println(" host >>>>>>>>>>>>>> " + host);
			System.out.println(" referer >>>>>>>>>>>>" + referer==null ? "" : referer);
			
			for(String url : null_n_Url)
			{
				if( currentUrl.equals( url ) )  
				{
					check = true;
					break;
				}
			}
			if( check==false )
			{
				if( referer==null || "".equals( referer ) )
				{
					return check;
				}else{
					if( !referer.contains( host ) )
					{
						return check;
					}
				}
				check = true;
			}
		}
		catch(Exception ex)
		{
			// error
		}
		return check;
	}
	
	// 허용할 IP 체크 ( IP 차단 _ 화이트리스트 방식 )
	public boolean allowIpCheck (HttpServletRequest request)
	{
		String[] allowIP = {"127.0.0.1","0:0:0:0:0:0:0:1"};
		boolean check = false;
		try 
		{
		//  Mapping Url 이 아닐 경우
		//	String currentUrl = request.getRequestURL().toString();
			String currentIp = request.getRemoteAddr(); 
			System.out.println("currenIp >>>>>>>> " + currentIp);
			
			for(String ip : allowIP)
			{
				if ( currentIp.contains(ip) )
				{
					check = true;
					break;
				}
			}
		}
		catch(Exception ex)
		{
			// error
		}
		return check;
	}
	
	// 허용할 URL 체크 ( URL 차단 _ 화이트리스트 방식 )
	public boolean allowURLCheck (HttpServletRequest request)
	{
		String[] allowURL = {"http://localhost:8080/"};
		boolean check = false;
		try 
		{
		//  Mapping Url 이 아닐 경우
		//	String currentUrl = request.getRequestURL().toString();
			String currentUrl = request.getScheme() + "://" + request.getServerName();
				   currentUrl += (request.getServerPort()==80?"":":"+request.getServerPort()); 
				   currentUrl += request.getRequestURI();
		    System.out.println(" currentUrl >>>>>>>>>>>>>>>>>>>> " + currentUrl);

			for(String url : allowURL)
			{
				if ( currentUrl.contains(url) )
				{
					check = true;
					break;
				}
			}
		}
		catch(Exception ex)
		{
			// error
		}
		return check;
	}
	
	// SSL처리할 URL 체크 ( 암호화 되지 않은 전송 처리 )
	public boolean sslCheck (HttpServletRequest request)
	{
		// ssl 처리 하려는 url 리스트
		String[] sslURL = {};
		boolean check = false;
		try 
		{
			// mapping Url 이 아닐 경우
			// String currentUrl = request.getRequestURL().toString();
			String currentUrl = request.getScheme() + "://" + request.getServerName();
				   currentUrl += (request.getServerPort()==80?"":":"+request.getServerPort()); 
				   currentUrl += request.getAttribute("javax.servlet.forward.request_uri");
				   
			for(String url : sslURL)
			{
				if ( currentUrl.contains(url) )
				{
					check = true;
					break;
				}
			}
		}
		catch(Exception ex)
		{
			// error
		}
		return check;
	}
	
	// return https 처리된 url
	public String getHttpsUrl(HttpServletRequest request)
	{
		// 반환할 URL
		String resultUrl = "";

		// SSL 처리 체크
		if ( sslCheck(request) )
		{
			try 
			{
				resultUrl = request.getScheme() + "://" + request.getServerName();
				resultUrl += (request.getServerPort()==80?"":":"+request.getServerPort()); 
				resultUrl += request.getAttribute("javax.servlet.forward.request_uri");
				
				String https_url = resultUrl.replaceAll("http:/", "https:/");
				resultUrl = request.getQueryString()==null ? 
						"location.href='"+https_url+"';" : "location.href='"+https_url+"?"+request.getQueryString()+"';";
				
			}catch(Exception ex)
			{
				// error
			}
		}
		
		return resultUrl;
	}
	
}

