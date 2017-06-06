package com.b2b.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.b2b.pagecomponent.PageComponent;
import com.b2b.stringfilter.Snippet;
import com.b2b.stringfilter.StringFilter;
import com.b2b.userlist.UserList;

/**
 * 
 */
@Controller
public class B2bController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(B2bController.class);

	UserList userList = new UserList();

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = PageComponent.root)
	public String filter(Locale locale, Model model, HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		System.out.println("++++++++++++++++++++++++++ 회원 세팅 시작 " );
		
		// 회원 세팅
		userList.putUser();
		
		return PageComponent.login;
	}
	
	@RequestMapping(value = PageComponent.filter)
	public String loginProc(Locale locale, HttpServletRequest request, HttpServletResponse response, Model model) {
		try {

			List<HashMap<String, String>> user = userList.userList;
			
			boolean userCheck = false;
			for ( HashMap<String, String> data : user) 
			{
				if( data.containsKey( request.getParameter("id") ) )
				{
					System.out.println(" 해당 사용자는 가입된 회원입니다.");
					userCheck = true;
					break;	
				}
			}
			if ( !userCheck )
			{
				System.out.println(" 가입된 회원이 아닙니다. ");
			}
			
			// xml 프로퍼티 테스트
			PageComponent p = new PageComponent();     
			p.print();
			
			/*
			 * 최신 버전
			 *	newCleanFilter.cleanXssSqlIj( 처리할 문자열 , 처리종류 < xss or sqlIj >, 예외문자1, 예외문자2 ) 
			 *  구 버전 
			 * oldCleanFilter.cleanXssSqlIj( 처리할 문자열 , 처리종류 < xss or sqlIj > )
			 */
			System.out.println(" 필터 처리 시작");
			System.out.println( "log >>>> request id : " + new String( request.getParameter("id").getBytes("8859_1"), "EUC-KR") );
			System.out.println( "log >>>> request pwd : " + request.getParameter("pwd"));
			
			/**
			 * source 크로스 사이트 스크립팅, SQL인젝션 (2016/07 ~ 2016/07)
			 * @param id, pwd, msg 
			 * 2016/07/10 AC916641
			 */
			String id = Snippet.cleanXssSqlIj(request.getParameter("id"), "xss");
			String pwd = StringFilter.cleanXssSqlIj(request.getParameter("pwd"), "sqlIj");
			
			System.out.println( "log >>>> 필터된 id : " + id);
			System.out.println( "log >>>> 필터된 pwd : " + pwd);
			
			model.addAttribute("id", id );
			model.addAttribute("pwd", pwd );
			
		}
		catch (Exception ex)
		{
			
		}
		
		return PageComponent.filter;
	}
	
	
	@RequestMapping(value = PageComponent.filterProc)
	public String filterProc(Locale locale, HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			
			/*
			 * 최신 버전
			 *	newCleanFilter.cleanXssSqlIj( 처리할 문자열 , 처리종류 < xss or sqlIj >, 예외문자1, 예외문자2 ) 
			 *  구 버전 
			 * oldCleanFilter.cleanXssSqlIj( 처리할 문자열 , 처리종류 < xss or sqlIj > )
			 */
			System.out.println(" 필터 처리 시작");
			System.out.println( "log >>>> request id : " + new String( request.getParameter("id").getBytes("8859_1"), "EUC-KR") );
			System.out.println( "log >>>> request pwd : " + request.getParameter("pwd"));
			System.out.println( "log >>>> request msg : " + request.getParameter("msg"));
			
			/**
			 * source 크로스 사이트 스크립팅, SQL인젝션 (2016/07 ~ 2016/07)
			 * @param id, pwd, msg 
			 * 2016/07/10 AC916641
			 */
			String id = Snippet.cleanXssSqlIj(request.getParameter("id"), "xss");
			String pwd = StringFilter.cleanXssSqlIj(request.getParameter("pwd"), "sqlIj");
			String msg = Snippet.cleanXssSqlIj(request.getParameter("msg"),"xss");
			
			System.out.println( "log >>>> 필터된 id : " + id);
			System.out.println( "log >>>> 필터된 pwd : " + pwd);
			System.out.println( "log >>>> 필터된 msg : " + msg);
			
			model.addAttribute("id", id );
			model.addAttribute("pwd", pwd );
			model.addAttribute("msg", msg );
			
		}
		catch (Exception ex)
		{
			
		}
		
		return PageComponent.filter;
	}
	
	
	@RequestMapping(value = PageComponent.sessionCheck)
	public String sessionCheck(Locale locale, Model model) {
		
		return PageComponent.sessionCheck;
		
	}

	/**
	 * logOutConfirm_pop 페이지
	 */
	@RequestMapping(value = PageComponent.logOutConfirm_pop)
	public String logOutConfirm_pop(Locale locale, Model model, HttpServletRequest request) {
		
		System.out.println( "session " + request.getSession().getId());
		
		return PageComponent.logOutConfirm_pop;
		
	}
}
