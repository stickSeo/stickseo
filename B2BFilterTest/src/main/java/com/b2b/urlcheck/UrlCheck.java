package com.b2b.urlcheck;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;

public class UrlCheck {
	
	// ũ�ν� ����Ʈ ��û���� ����
	public boolean refereCheck (HttpServletRequest request)
	{
		// null üũ ���� ���� url ����Ʈ
		String[] null_n_Url = {"http://localhost:8080/","http://localhost:8080/login"};
		String	referer = request.getHeader("referer");
		boolean check = false;
		
		try 
		{
			// ���� url
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
	
	// ����� IP üũ ( IP ���� _ ȭ��Ʈ����Ʈ ��� )
	public boolean allowIpCheck (HttpServletRequest request)
	{
		String[] allowIP = {"127.0.0.1","0:0:0:0:0:0:0:1"};
		boolean check = false;
		try 
		{
		//  Mapping Url �� �ƴ� ���
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
	
	// ����� URL üũ ( URL ���� _ ȭ��Ʈ����Ʈ ��� )
	public boolean allowURLCheck (HttpServletRequest request)
	{
		String[] allowURL = {"http://localhost:8080/"};
		boolean check = false;
		try 
		{
		//  Mapping Url �� �ƴ� ���
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
	
	// SSLó���� URL üũ ( ��ȣȭ ���� ���� ���� ó�� )
	public boolean sslCheck (HttpServletRequest request)
	{
		// ssl ó�� �Ϸ��� url ����Ʈ
		String[] sslURL = {};
		boolean check = false;
		try 
		{
			// mapping Url �� �ƴ� ���
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
	
	// return https ó���� url
	public String getHttpsUrl(HttpServletRequest request)
	{
		// ��ȯ�� URL
		String resultUrl = "";

		// SSL ó�� üũ
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

