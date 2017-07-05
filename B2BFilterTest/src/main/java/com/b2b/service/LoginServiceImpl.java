package com.b2b.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.b2b.dao.LoginDAO;

@Service
public class LoginServiceImpl implements LoginService{

	@Resource(name="loginDAO")
    private LoginDAO loginDAO;
	
	@Override
	public int loginCheck(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return loginDAO.loginCheck(map);
	}
}
