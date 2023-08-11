package Member.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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


	 public Seller getSellerdata(int chnum) {
	        Seller seller = new Seller();
	        String sql = "SELECT s.* "
	                   + "FROM channellist c "
	                   + "INNER JOIN seller s ON c.ownerid = s.userid "
	                   + "WHERE c.chNum = ?";

	        try (Connection con = ds.getConnection();
	             PreparedStatement pstmt = con.prepareStatement(sql);) {

	            pstmt.setInt(1, chnum);

	            try (ResultSet rs = pstmt.executeQuery()) {
	                if (rs.next()) {
	                    seller.setUserId(rs.getString("USERID"));
	                    seller.setSellerPhone(rs.getString("SELLERPHONE"));
	                    seller.setSellerEmail(rs.getString("SELLEREMAIL"));
	                    seller.setSellerJoindate(rs.getString("SELLERJOINDATE"));
	                }
	            }
	        } catch (SQLException se) {
	            se.printStackTrace();
	        } catch (Exception e) {
	            System.out.println("getDetail() 에러: " + e);
	        }
	        return seller;
	    }

}

