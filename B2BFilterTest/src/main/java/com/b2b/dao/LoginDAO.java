package com.b2b.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("loginDAO")
public class LoginDAO extends AbstractDAO{
	
	@SuppressWarnings("unchecked")
	public int loginCheck(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return (Integer)selectOne("login.loginCheck", map);
	}

	@SuppressWarnings("unchecked")
	public int joinCheck(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return (Integer)selectOne("login.joinCheck", map);
	}
	
	@SuppressWarnings("unchecked")
	public int loginUpdate(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return (Integer)update("login.loginUpdate", map);
	}

	@SuppressWarnings("unchecked")
	public int bfatUpdate(Map<String, Object> map) {
		return (Integer)update("login.bfatUpdate", map);
	}
}
