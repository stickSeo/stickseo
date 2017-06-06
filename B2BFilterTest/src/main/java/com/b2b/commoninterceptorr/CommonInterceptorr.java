package com.b2b.commoninterceptorr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.b2b.pagecomponent.PageComponent;
import com.b2b.urlcheck.*;

public class CommonInterceptorr extends HandlerInterceptorAdapter{
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try
        {
        	 // 에러, 팝업 페이지 일경우 체크 안함
	    	 if ( request.getRequestURI().startsWith(PageComponent.error) 
	    			||  request.getRequestURI().startsWith(PageComponent.pop) )
	         {
	        	  return true; // error페이지, popup 일경우 인터셉터 통과.
	         }
	    	 // 크로스 사이트 요청위조
	    	 if ( !UrlCheck.refereCheck(request) )
	    	 {  	
	    		response.sendRedirect(PageComponent.errorCsrf);
	    		return false;
	    	 }
	    	 // 허용가능한 url , ip를 체크
	    	 if( !UrlCheck.allowURLCheck(request) 
	    				|| !UrlCheck.allowIpCheck(request))
    		 {
    			HttpSession session = request.getSession();
    			if(session!=null)
    			{
    				session.invalidate();
    			}
    			response.sendRedirect(PageComponent.errorCsrf);
	    		return false;
    		 }
	    	 
        } catch (Exception e) {
           // e.printStackTrace();
        }
        return true;
    }

}
