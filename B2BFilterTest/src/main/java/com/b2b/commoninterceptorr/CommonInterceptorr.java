package com.b2b.commoninterceptorr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.b2b.multilogincheck.MultiLoginCheck;
import com.b2b.pagecomponent.PageComponent;

public class CommonInterceptorr extends HandlerInterceptorAdapter{
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try
        {
        	// �α����� ��������� Ȯ��
        	if(request.getSession().getAttribute("id")==null){
        		System.out.println("����ڴ� �α׾ƿ����� ");
        		response.sendRedirect(PageComponent.root);
        		return false;
        	}
        	System.out.println(request.getSession().getAttribute("id")+" ����ڴ� �α�����");
        	// ����� ���� Ȯ��
        	System.out.println("���� ���ͼ��� ����");
        	 // ����, �˾� ������ �ϰ�� üũ ����
//	    	 if ( request.getRequestURI().startsWith(PageComponent.error) 
//	    			||  request.getRequestURI().startsWith(PageComponent.pop) )
//	         {
//	        	  return true; // error������, popup �ϰ�� ���ͼ��� ���.
//	         }
//	    	 // ũ�ν� ����Ʈ ��û����
//	    	 if ( !UrlCheck.refereCheck(request) )
//	    	 {  	
//	    		response.sendRedirect(PageComponent.errorCsrf);
//	    		return false;
//	    	 }
//	    	 // ��밡���� url , ip�� üũ
//	    	 if( !UrlCheck.allowURLCheck(request) 
//	    				|| !UrlCheck.allowIpCheck(request))
//    		 {
//    			HttpSession session = request.getSession();
//    			if(session!=null)
//    			{
//    				session.invalidate();
//    			}
//    			response.sendRedirect(PageComponent.errorCsrf);
//	    		return false;
//    		 }
	    	 
        } catch (Exception e) {
           // e.printStackTrace();
        }
        return true;
    }

}
