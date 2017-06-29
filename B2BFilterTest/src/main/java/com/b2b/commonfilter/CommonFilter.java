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
		 	boolean accept = true;
		 	
		if(request instanceof HttpServletRequest){
			
			 System.out.println("1111111 accept ������� : " + accept);
			 // ����, �˾� ������ �ϰ�� üũ ����
			 System.out.println(((HttpServletRequest)request).getRequestURI());

		   	 if ( ((HttpServletRequest)request).getRequestURI().startsWith(PageComponent.error) 
		   			||  ((HttpServletRequest)request).getRequestURI().startsWith(PageComponent.pop) )
		     {
				System.out.println("����, �˾��������ϰ��");
		   		chain.doFilter(request, response);
		     }else{
			   	 // ũ�ν� ����Ʈ ��û����
			   	 if ( !UrlCheck.refereCheck(((HttpServletRequest)request)) )
			   	 {  	
					System.out.println("��û�����ϰ��");
					accept = false;
			   		((HttpServletResponse)response).sendRedirect(PageComponent.errorCsrf);
			   		return;
			   	 }
		   	 	 // ��밡���� url , ip�� üũ
			   	 if( !UrlCheck.allowURLCheck(((HttpServletRequest)request)) 
			   				|| !UrlCheck.allowIpCheck(((HttpServletRequest)request)))
				 {
				 	System.out.println("��밡���� url,ip�� �ƴҰ�� üũ�ϰ��");
				 	accept = false;
					HttpSession session = ((HttpServletRequest)request).getSession();
					if(session!=null)
					{
						session.invalidate();
					}
					((HttpServletResponse)response).sendRedirect(PageComponent.errorCsrf);
					return;
				}
				 System.out.println("222222 accept ������� : " + accept);
			   	if(accept==true){
			   		chain.doFilter(request, response);
			   	}
		     }
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		filterConfig = null;
	}
}
