package com.b2b.multilogincheck;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class MultiLoginCheck implements HttpSessionBindingListener {

	
	private static MultiLoginCheck loginManager = null;
	// �α����� ����� �ؽ����̺�� ����
	private static Hashtable<String, String> ht = new Hashtable<String, String>();
	
	public MultiLoginCheck(){  }

	// ���� ����� �α��� üũ
	public String LoginChk(String user_id, String currIp){
		
		String loginCheck = "N";
		try{
			
			if ( ht.containsKey(user_id) ) 
			{
				// �α����� ����
				loginCheck = "Y";
			}
			
		} catch (Exception ex) {
			System.out.println(" Exception ! ");
			System.out.println(" MESSAGE =  " + ex.getMessage());
			System.out.println( ex );
			ex.printStackTrace();
		}
		return loginCheck;
		
	}
	
	// �α׾ƿ�
	public void crrIpClear(String user_id){
		
		try{
			
			ht.remove(user_id);
			
		}catch(Exception ex){
			System.out.println(" Exception ! ");
			System.out.println(" MESSAGE =  " + ex.getMessage());
			System.out.println( ex );
			ex.printStackTrace();
		}
		
	}
	
	// �α����� ����� ����Ʈ
	public void getUserList(){
		try{
			Enumeration<String> em = ht.keys();
			while(true){
				 if( em.hasMoreElements() ){
					 System.out.println("�α����� �����");
					 System.out.println(">>>>"+ em.nextElement()); 
				 }else  break;  
			}
		}catch(Exception ex){
			System.out.println(" Exception ! ");
			System.out.println(" MESSAGE =  " + ex.getMessage());
			System.out.println( ex );
			ex.printStackTrace();
		}
	}
	
	public static synchronized MultiLoginCheck getInstance(){
		 if(loginManager == null){
			 loginManager = new MultiLoginCheck();
		}
		return loginManager;
	}
	
	//���� ����
	public void setSession(HttpSession session, String userID){
		session.setAttribute(userID, this.getInstance()); //������ �Ӽ��� �α��θŴ����� ������ �����մϴ�.
		// �α���
		ht.put(userID, userID);

	}
	//���� ������ ��
	public void valueBound(HttpSessionBindingEvent event) {
		HttpSession hs = event.getSession();
		System.out.println("SessionListener.valueBound() : "+hs.getAttribute("id"));

	}
	 
	//���� ���涧
	public void valueUnbound(HttpSessionBindingEvent event) {
	   System.out.println("�α��� ���� ���� ������ ����");
	   try {  
		 ht.remove(event.getName());
	   } catch (Exception ex) 
	   { 
		   System.out.println(" Exception ! ");
		   System.out.println(" MESSAGE =  " + ex.getMessage());
		   System.out.println( ex );
	       ex.printStackTrace();
	   }
	   System.out.println("SessionListener.valueUnbound() : "+event.getName());
	}
	
}
