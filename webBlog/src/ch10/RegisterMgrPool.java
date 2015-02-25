package ch10;

import java.util.*;
import java.sql.*;
import ch10.RegisterBean;

public class RegisterMgrPool {

	private DBConnectionMgr pool = null;
	
//	private final String JDBC_DRIVER = "org.gjt.mm.mysql.Driver";
//	private final String JDBC_URL = "jdbc:mysql://localhost:3306/test";
//	private final String JDBC_USER = "javauser";
//	private final String JDBC_PASS = "1234";
//	
	private final String Table = "tblRegister";
	
	public RegisterMgrPool(){
		try{
//			Class.forName(JDBC_DRIVER).newInstance();
			pool = DBConnectionMgr.getInstance();
		}catch(Exception ex){
			System.out.println("Error : JDBC 드라이버 로딩 실패");
		}
	}

	
	/*
	 * 회원 목록
	 * 
	*/
	public ArrayList getMemberList(){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList arrayList = new ArrayList();

		try{
			
//			conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASS);
			conn = pool.getConnection();
			String sql = "select * from " +Table;
			pstmt = conn.prepareStatement(sql);
			rs  = pstmt.executeQuery();
			
			while(rs.next()){
				RegisterBean regBean = new RegisterBean();
				regBean.setId(rs.getString("id"));
				regBean.setPasswd(rs.getString("passwd"));
				regBean.setName(rs.getString("name"));
				regBean.setNum1(rs.getString("num1"));
				regBean.setNum2(rs.getString("num2"));
				regBean.setEmail(rs.getString("email"));
				regBean.setPhone(rs.getString("phone"));
				regBean.setZipcode(rs.getString("zipcode"));
				regBean.setAddress(rs.getString("address"));
				regBean.setJob(rs.getString("job"));
				arrayList.add(regBean);
			
			}
			
		}catch(Exception ex){
			System.out.println("Exception : " + ex);
		}finally{
			
//			if(rs!=null) try{rs.close();}catch(SQLException ex){}
//			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
//			if(conn!=null) try{conn.close();}catch(SQLException ex){}
			pool.freeConnection(conn,pstmt,rs);
			
		}
		
		return arrayList;
		
	}
	
	
	/*
	 * 아이디 비밀번호 체크
	 * 
	*/
	public boolean passCheck(String cust_id, String cust_passwd){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean loginCon = false;
		
		try{
			
			conn = pool.getConnection();
			
			String sql = "select count(*) from " +Table+" where id=? and passwd=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cust_id);
			pstmt.setString(2, cust_passwd);
			rs = pstmt.executeQuery();
			
			rs.next();
			if(rs.getInt(1)>0) loginCon = true;
			

		}catch(Exception ex){
			System.out.println("Exception : " + ex);
		}finally{
			
			pool.freeConnection(conn,pstmt,rs);
			
		}
		return loginCon;
	}
}
