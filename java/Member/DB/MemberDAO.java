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
	public int isuserId(String userId, String userPw) {
	    int result = -1;
	    String sql = "select userId, userPw from itda_user where userId = ? and userPw = ?";

	    try (Connection con = ds.getConnection();
	         PreparedStatement pre = con.prepareStatement(sql);) {

			pre.setString(1, userId);
			pre.setString(2, userPw);

			try (ResultSet rs = pre.executeQuery()) {
				if (rs.next()) {
					if (rs.getString(2).equals(userPw)) {
						result = 1;
					} else {
						result = 0;
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return result;

	}

	 public int insert(Member m) {
	        int result = 0;
	        String sql = "INSERT INTO itda_user (userId, userPw, userName, userBirth, userGender, userPhone, "
	                + "userAddress1, userAddress2, userPost, userEmail, userCategory, userJoindate, statusId) "
	                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate, 1)";

	        try (Connection con = ds.getConnection(); PreparedStatement pre = con.prepareStatement(sql);) {
	            // ... 기존의 코드 ...
	            result = pre.executeUpdate();
	            if (result == 1) {
	                System.out.println("회원정보 삽입 완료");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return result;
	    }

}
