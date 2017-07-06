package com.b2b.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.b2b.multilogincheck.MultiLoginCheck;
import com.b2b.pagecomponent.PageComponent;
import com.b2b.securityUtil.SecurityUtil;
import com.b2b.service.LoginServiceImpl;
import com.b2b.stringfilter.Snippet;
import com.b2b.stringfilter.StringFilter;
import com.b2b.userlist.UserList;

/**
 * 
 */
@Controller
public class B2bController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(B2bController.class);
	
	@Autowired
	private LoginServiceImpl loginService;
	 
	UserList userList = new UserList();

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = PageComponent.root)
	public String setting(Locale locale, Model model, HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		System.out.println("++++++++++++++++++++++++++ �α��� " );
		request.getSession().invalidate();
		// ȸ�� ����
		//userList.putUser();
		return PageComponent.login;
	}
	
	
	@RequestMapping(value = PageComponent.loginProc)
	public void loginProc(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		logger.info("Welcome home! The client locale is {}.", locale);
		try {
			System.out.println("�α��� proc");
			SecurityUtil secure = new SecurityUtil();
			MultiLoginCheck mlt = new MultiLoginCheck();
			Map<String,Object> user = new HashMap<String, Object>();
			user.put("id",request.getParameter("id"));
			user.put("pwd",secure.ecrtyptSHA256( request.getParameter("pwd")));
			HttpSession session = request.getSession();
			String multChk = mlt.LoginChk((String)user.get("id"), request.getLocalAddr());
			int chk = loginService.loginCheck(user);
			if(chk==1){
				if("N".equals( multChk ))
				{	System.out.println("�α��� ����");
					mlt.setSession(session, request.getParameter("id"));
					response.sendRedirect(PageComponent.filter);
					return;
				}else{
					if(session.getId()!=null){
						session.invalidate();
					}
					response.sendRedirect(PageComponent.root);
					System.out.println("��Ƽ�α��� ����");
					return;
				}
			}else{
				if(session.getId()!=null){
					session.invalidate();
				}
				response.sendRedirect(PageComponent.root);
				System.out.println("�α��� ����");
				return;
			}
		}catch(Exception ex){
			
		}
		// ȸ�� ����
		//userList.putUser();
	}
	
	@RequestMapping(value = PageComponent.filter)
	public String filterVeiw(Locale locale, HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			// xml ������Ƽ �׽�Ʈ
			PageComponent p = new PageComponent();     
			p.print();
			/*
			 * �ֽ� ����
			 *	newCleanFilter.cleanXssSqlIj( ó���� ���ڿ� , ó������ < xss or sqlIj >, ���ܹ���1, ���ܹ���2 ) 
			 *  �� ���� 
			 * oldCleanFilter.cleanXssSqlIj( ó���� ���ڿ� , ó������ < xss or sqlIj > )
			 */
			System.out.println(" ���� ó�� ����");
			System.out.println( "log >>>> request id : " + new String( request.getParameter("id").getBytes("8859_1"), "EUC-KR") );
			System.out.println( "log >>>> request pwd : " + request.getParameter("pwd"));
			
			/**
			 * source ũ�ν� ����Ʈ ��ũ����, SQL������ (2016/07 ~ 2016/07)
			 * @param id, pwd, msg 
			 * 2016/07/10 AC916641
			 */
			String id = Snippet.cleanXssSqlIj(request.getParameter("id"), "xss");
			String pwd = StringFilter.cleanXssSqlIj(request.getParameter("pwd"), "sqlIj");
			
			System.out.println( "log >>>> ���͵� id : " + id);
			System.out.println( "log >>>> ���͵� pwd : " + pwd);
			
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
			 * �ֽ� ����
			 *	newCleanFilter.cleanXssSqlIj( ó���� ���ڿ� , ó������ < xss or sqlIj >, ���ܹ���1, ���ܹ���2 ) 
			 *  �� ���� 
			 * oldCleanFilter.cleanXssSqlIj( ó���� ���ڿ� , ó������ < xss or sqlIj > )
			 */
			System.out.println(" ���� ó�� ����");
			System.out.println( "log >>>> request id : " + new String( request.getParameter("id").getBytes("8859_1"), "EUC-KR") );
			System.out.println( "log >>>> request pwd : " + request.getParameter("pwd"));
			System.out.println( "log >>>> request msg : " + request.getParameter("msg"));
			
			/**
			 * source ũ�ν� ����Ʈ ��ũ����, SQL������ (2016/07 ~ 2016/07)
			 * @param id, pwd, msg 
			 * 2016/07/10 AC916641
			 */
			String id = Snippet.cleanXssSqlIj(request.getParameter("id"), "xss");
			String pwd = StringFilter.cleanXssSqlIj(request.getParameter("pwd"), "sqlIj");
			String msg = Snippet.cleanXssSqlIj(request.getParameter("msg"),"xss");
			
			System.out.println( "log >>>> ���͵� id : " + id);
			System.out.println( "log >>>> ���͵� pwd : " + pwd);
			System.out.println( "log >>>> ���͵� msg : " + msg);
			
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
	 * logOutConfirm_pop ������
	 */
	@RequestMapping(value = PageComponent.logOutConfirm_pop)
	public String logOutConfirm_pop(Locale locale, Model model, HttpServletRequest request) {
		
		System.out.println( "session " + request.getSession().getId());
		
		return PageComponent.logOutConfirm_pop;
		
	}
}
