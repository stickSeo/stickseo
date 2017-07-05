package com.b2b.service;

import java.util.List;
import java.util.Map;

public interface LoginService {

	// 로그인 성공여부
	int loginCheck(Map<String, Object> map) throws Exception;

	 
}
