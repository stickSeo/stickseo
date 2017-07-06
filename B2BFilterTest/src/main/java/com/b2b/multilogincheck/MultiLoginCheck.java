package com.b2b.multilogincheck;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class MultiLoginCheck implements HttpSessionBindingListener {

	
	private static MultiLoginCheck loginManager = null;
	// 로그인한 사용자 해시테이블로 관리
	private static Hashtable<String, String> ht = new Hashtable<String, String>();
	
	public MultiLoginCheck(){  }

	// 접근 사용자 로그인 체크
	public String LoginChk(String user_id, String currIp){
		
		String loginCheck = "N";
		try{
			
			if ( ht.containsKey(user_id) ) 
			{
				// 로그인한 상태
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
	
	// 로그아웃
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
	
	// 로그인한 사용자 리스트
	public void getUserList(){
		try{
			Enumeration<String> em = ht.keys();
			while(true){
				 if( em.hasMoreElements() ){
					 System.out.println("로그인한 사용자");
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
	
	//세션 생성
	public void setSession(HttpSession session, String userID){
		session.setAttribute(userID, this.getInstance()); //세션의 속성에 로그인매니져의 정보를 설정합니다.
		// 로그인
		ht.put(userID, userID);

	}
	//세션 성립될 때
	public void valueBound(HttpSessionBindingEvent event) {
		HttpSession hs = event.getSession();
		System.out.println("SessionListener.valueBound() : "+hs.getAttribute("id"));

	}
	 
	//세션 끊길때
	public void valueUnbound(HttpSessionBindingEvent event) {
	   System.out.println("로그인 정보 삭제 리스너 시작");
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
