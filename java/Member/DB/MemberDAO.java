package Member.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	private DataSource ds;
	
	
	
	public MemberDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		}catch (Exception e) {
			System.out.println("DB 연결 실패 : " + e);
		}
	}



	public int isuserId(String userId, String userPw) {			//로그인 시 id와 pw가 있는지 확인
		int result = -1;
		String sql = "select userId, userPw from itda_user where userId = ? ";
		
		try (Connection con = ds.getConnection();
			PreparedStatement pre = con.prepareStatement(sql);) {
			
			pre.setString(1, userId);
			
			try (ResultSet rs = pre.executeQuery()) {
				if (rs.next()) {
					if (rs.getString(2).equals(userPw)) {
						result = 1;
					}else {
						result = 0;
					}
				}
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}//Con end
		
		return result;
		
	}//isuserId end



	public int isuserId(String userId) {	//회원가입 중 중복확인버튼 클릭시 db와 비교해 userId가 있는지 확인
		int result = -1;
		String sql = "select uesrId from itda_user where userId = ? ";
		
		try (Connection con = ds.getConnection();
			PreparedStatement pre = con.prepareStatement(sql);) {
			
			pre.setString(1, userId);
			
			try (ResultSet rs = pre.executeQuery()) {
				if (rs.next()) {
					result = 0;
				}
			}
		
		}catch (Exception e) {
			e.printStackTrace();
		
		}//Con end
		
		return result;
		
	}//isuserId end



	public int insert(Member m) {
		int result = 0;
		
		String sql = "insert into itda_user "
				   + "(userId, userPw, userName, userBirth, userGender, userPhone, "
				   + "userAddress1, userAddress2, userPost, userEmail, userCategory, "
				   + "userJoindate, statusId, updateDate) "
				   + "values(?,?,?,?,?,? "
				   + "?,?,?,?,?,"
				   + "sysdate, 1, sysdate)";
		
		try(Connection con = ds.getConnection();
			PreparedStatement pre = con.prepareStatement(sql);) {
			
			pre.setString(1, m.getUserId());			//userId
			pre.setString(2, m.getUserPw());			//userPw
			pre.setString(3, m.getUserName());			//userName
			pre.setInt(4, m.getUserBirth());			//userBirth
			pre.setString(5, m.getUserGender());		//userGender
			pre.setString(6, m.getUserPhone());			//userPhone
			pre.setString(7, m.getUserAddress1());		//userAddress1
			pre.setString(8, m.getUserAddress2());		//userAddress2
			pre.setString(9, m.getUserPost());			//userPost
			pre.setString(10, m.getUserEmail());		//userEmail
			pre.setString(11, m.getUserCategory());		//userCategory
			pre.setInt(12, m.getUserJoindate());		//userJoindate
			pre.setInt(13, m.getUpdateDate());			//updateDate
			
			result = pre.executeUpdate();
			if (result == 1) {
				System.out.println("회원정보 삽입 완료");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
