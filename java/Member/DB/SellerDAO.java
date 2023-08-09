package Member.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class SellerDAO {
	private DataSource ds;
	private int result = 0;
	
	public SellerDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		}catch (Exception e) {
			System.out.println("DB 연결 실패 : " + e);
		}
	}


	public int insertSeller(Seller s, String userId) {						//판매회원 데이터 삽입
		String sql = "insert into seller "
				   + "values(?, ?, ?, sysdate) ";
		
		try(Connection con = ds.getConnection();
			PreparedStatement pre = con.prepareStatement(sql);) {
				
			pre.setString(1, userId);
			pre.setString(2, s.getSellerPhone());
			pre.setString(3, s.getSellerEmail());
			
			result = pre.executeUpdate();									//삽입 성공시 1
			System.out.println("판매회원 DB 삽입 성공");
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return result;
	
	}//insertSeller end
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
