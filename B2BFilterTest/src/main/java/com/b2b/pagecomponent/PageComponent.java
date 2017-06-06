package com.b2b.pagecomponent;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/*
 * 	������ Property
 * 
 */
@Component
public class PageComponent {

	// ����Ʈ ��Ʈ
	public static final String root = "/";
	// ���� üũ
	public static final String sessionCheck = "/sessionCheck";
	// �α��� ������
	public static final String login = "/login";
	// �α��� ����
	public static final String loginProc = "/loginProc";
	// ���� �׽�Ʈ ������
	public static final String filter = "/filter";
	// ���� �׽�Ʈ ����
	public static final String filterProc = "/filterProc";
	// ũ�ν� ����Ʈ ��û ���� ���� ������
	public static final String errorCsrf = "/error/error";
	// 404 ���� ������
	public static final String error404 = "/error/error404";
	// �Խ��� ��� ������
	public static final String boaldList = "/sample/boardList";
	// �Խ��� �˻�
	public static final String searchList = "/sample/search";
	// �α׾ƿ� Ȯ�� �˾�
	public static final String logOutConfirm_pop = "/pop/logOutConfirm_pop";
	
	// ������
	public static final String pop = "/pop";
	public static final String error = "/error";
	
	// ������Ƽ �� ��������
	@Value("#{props['loginProc']}") 
	private String hello;

	public void print() {
	   System.out.println("loginProc property �� : " + hello);
	}
	
}
