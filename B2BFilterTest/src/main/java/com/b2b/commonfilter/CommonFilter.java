package com.b2b.commonfilter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.b2b.pagecomponent.PageComponent;
import com.b2b.urlcheck.UrlCheck;

@Service("filter")
public class CommonFilter implements Filter{
	
	private FilterConfig filterConfig;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		filterConfig = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		 	System.out.println("���� ���� �׽�Ʈ ����");

		if(request instanceof HttpServletRequest){
			 // ����, �˾� ������ �ϰ�� üũ ����
		   	 if ( ((HttpServletRequest)request).getRequestURI().startsWith(PageComponent.error) 
		   			||  ((HttpServletRequest)request).getRequestURI().startsWith(PageComponent.pop) )
		     {
				 	System.out.println("����, �˾��������ϰ��");
		     }else{
				chain.doFilter(request, response);
		     }
		   	 // ũ�ν� ����Ʈ ��û����
		   	 if ( !UrlCheck.refereCheck(((HttpServletRequest)request)) )
		   	 {  	
				System.out.println("��û�����ϰ��");
		   		((HttpServletResponse)response).sendRedirect(PageComponent.errorCsrf);
		   	 }
	   	 	 // ��밡���� url , ip�� üũ
		   	 if( !UrlCheck.allowURLCheck(((HttpServletRequest)request)) 
		   				|| !UrlCheck.allowIpCheck(((HttpServletRequest)request)))
			 {
				 	System.out.println("��밡���� url,ip�� �ƴҰ�� üũ�ϰ��");
					HttpSession session = ((HttpServletRequest)request).getSession();
					if(session!=null)
					{
						session.invalidate();
					}
					((HttpServletResponse)response).sendRedirect(PageComponent.errorCsrf);
			}
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		filterConfig = null;
	}
}
