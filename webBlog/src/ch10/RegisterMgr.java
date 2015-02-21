package ch10;

import java.util.*;
import java.sql.*;
import ch10.RegisterBean;

public class RegisterMgr {

	private final String JDBC_DRIVER = "org.gjt.mm.mysql.Driver";
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/test";
	private final String JDBC_USER = "javauser";
	private final String JDBC_PASS = "1234";
	
	private final String Table = "tblRegister";

	
	public RegisterMgr(){
		try{
			Class.forName(JDBC_DRIVER).newInstance();
		}catch(Exception ex){
			System.out.println("Error : JDBC 드라이버 로딩 실패");
		}
	}

	public ArrayList getMemberList(){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList arrayList = new ArrayList();

		try{
			
			conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASS);
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
			
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null) try{conn.close();}catch(SQLException ex){}
			
		}
		
		return arrayList;
		
	}
}
