package com.b2b.pagecomponent;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/*
 * 	페이지 Property
 * 
 */
@Component
public class PageComponent {

	// 사이트 루트
	public static final String root = "/";
	// 세션 체크
	public static final String sessionCheck = "/sessionCheck";
	// 로그인 페이지
	public static final String login = "/login";
	// 로그인 수행
	public static final String loginProc = "/loginProc";
	// 필터 테스트 페이지
	public static final String filter = "/filter";
	// 필터 테스트 수행
	public static final String filterProc = "/filterProc";
	// 크로스 사이트 요청 위조 에러 페이지
	public static final String errorCsrf = "/error/error";
	// 404 에러 페이지
	public static final String error404 = "/error/error404";
	// 게시판 목록 페이지
	public static final String boaldList = "/sample/boardList";
	// 게시판 검색
	public static final String searchList = "/sample/search";
	// 로그아웃 확인 팝업
	public static final String logOutConfirm_pop = "/pop/logOutConfirm_pop";
	
	// 폴더명
	public static final String pop = "/pop";
	public static final String error = "/error";
	
	// 프로퍼티 값 가져오기
	@Value("#{props['loginProc']}") 
	private String hello;

	public void print() {
	   System.out.println("loginProc property 값 : " + hello);
	}
	
}
