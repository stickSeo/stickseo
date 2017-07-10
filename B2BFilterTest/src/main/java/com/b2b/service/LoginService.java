package com.b2b.service;

import java.util.List;
import java.util.Map;

public interface LoginService {

	// 로그인 성공여부
	int loginCheck(Map<String, Object> map) throws Exception;

	// 회원가입된 상태여부
	int joinCheck(Map<String, Object> map) throws Exception;
	
	// 로그인날짜 업데이트
	int loginUpdate(Map<String, Object> map) throws Exception;
	
	// 로그인 실패횟수 업데이트
	int bfatUpdate(Map<String, Object> map) throws Exception;
}
