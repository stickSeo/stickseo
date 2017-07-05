package com.b2b.securityUtil;

import java.security.MessageDigest;

public class SecurityUtil {

	public String ecrtyptSHA256(String str){
		String sha = "";
		try{
			MessageDigest sh = MessageDigest.getInstance("SHA-256");
			sh.update(str.getBytes());
			byte bytedata[] = sh.digest();
			StringBuffer sb = new StringBuffer();
			for(int i=0; i<bytedata.length; i++){
				sb.append(Integer.toString((bytedata[i] & 0xff)+0x100, 16).substring(1));
			}
			sha = sb.toString();

		}catch(Exception ex){
			
		}
		return sha;
	}
	
	public String ecrtyptMDP5(String str){
		String mdp = "";
		try{
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte bytedata[] = md.digest();
			StringBuffer sb = new StringBuffer();
			for(int i=0; i<bytedata.length; i++){
				sb.append(Integer.toString((bytedata[i] & 0xff)+0x100, 16).substring(1));
			}
			mdp = sb.toString();
		}catch(Exception ex){
			
		}
		return mdp;
	}
}
