package com.b2b.userlist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserList {

	public UserList() {}
	
	public HashMap<String, String> user = new HashMap<String, String>();
	public List<HashMap<String, String>> userList = new ArrayList<HashMap<String, String>>();
	
	// 사용자 계정 세팅
	public void putUser()
	{
		// 사용자 계정
		user.put("test", "1234");
		// 사용자 리스트 추가
		userList.add(user);
		
	}
	
	
	public boolean UserCheck(String user_id)
	{
		boolean check = false;
		
		if( userList.contains(user_id) )
		{
			check = true;
		}
		
		return check;
	}
	
}
