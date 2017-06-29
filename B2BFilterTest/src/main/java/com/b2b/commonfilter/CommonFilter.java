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
		
		 	System.out.println("공통 필터 테스트 시작");
		 	boolean accept = true;
		 	
		if(request instanceof HttpServletRequest){
			
			 System.out.println("1111111 accept 접근허용 : " + accept);
			 // 에러, 팝업 페이지 일경우 체크 안함
			 System.out.println(((HttpServletRequest)request).getRequestURI());

		   	 if ( ((HttpServletRequest)request).getRequestURI().startsWith(PageComponent.error) 
		   			||  ((HttpServletRequest)request).getRequestURI().startsWith(PageComponent.pop) )
		     {
				System.out.println("에러, 팝업페이지일경우");
		   		chain.doFilter(request, response);
		     }else{
			   	 // 크로스 사이트 요청위조
			   	 if ( !UrlCheck.refereCheck(((HttpServletRequest)request)) )
			   	 {  	
					System.out.println("요청위조일경우");
					accept = false;
			   		((HttpServletResponse)response).sendRedirect(PageComponent.errorCsrf);
			   		return;
			   	 }
		   	 	 // 허용가능한 url , ip를 체크
			   	 if( !UrlCheck.allowURLCheck(((HttpServletRequest)request)) 
			   				|| !UrlCheck.allowIpCheck(((HttpServletRequest)request)))
				 {
				 	System.out.println("허용가능한 url,ip가 아닐경우 체크일경우");
				 	accept = false;
					HttpSession session = ((HttpServletRequest)request).getSession();
					if(session!=null)
					{
						session.invalidate();
					}
					((HttpServletResponse)response).sendRedirect(PageComponent.errorCsrf);
					return;
				}
				 System.out.println("222222 accept 접근허용 : " + accept);
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
