package com.b2b.userlist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserList {

	public UserList() {}
	
	public HashMap<String, String> user = new HashMap<String, String>();
	public List<HashMap<String, String>> userList = new ArrayList<HashMap<String, String>>();
	
	// ����� ���� ����
	public void putUser()
	{
		// ����� ����
		user.put("test", "1234");
		// ����� ����Ʈ �߰�
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
