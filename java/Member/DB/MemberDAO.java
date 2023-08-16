package Member.DB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

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


	int result = -1;
	public int isuserId(String userId, String userPw) {            //로그인 시 id와 pw가 있는지 확인

		String sql = "select userId, userPw from itda_user where userId = ? ";

		try (Connection con = ds.getConnection();
			 PreparedStatement pre = con.prepareStatement(sql);) {

			pre.setString(1, userId);
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

	public int isuserId(String userId) {	//회원가입 중 중복확인버튼 클릭시 db와 비교해 userId가 있는지 확인
		
		String sql = "select userId from itda_user where userId = ? ";
		
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

	}


	public int insert(Member m) {
	    int result = 0;

	    String sql = "insert into itda_user "
	            + "(userId, userPw, userName, userBirth, userGender, userPhone, "
	            + "userAddress1, userAddress2, userPost, userEmail, userCategory, "
	            + "userJoindate, statusId, userProfile) "
	            + "values(?,?,?,?,?,?, "
	            + "?,?,?,?,?,"
	            + "SYSDATE, 1, ?)";


	    // LocalDate를 java.sql.Date로 변환
	    Date userBirth = null;
	    LocalDate dateOfBirth = m.getDateOfBirth();
	    if (dateOfBirth != null) {
	        userBirth = Date.valueOf(dateOfBirth);
	    }

	    try(Connection con = ds.getConnection();
	        PreparedStatement pre = con.prepareStatement(sql);) {

	        pre.setString(1, m.getUserId());          // userId
	        pre.setString(2, m.getUserPw());          // userPw
	        pre.setString(3, m.getUserName());        // userName
	        pre.setDate(4, userBirth);                // userBirth
	        pre.setString(5, m.getUserGender());      // userGender
	        pre.setString(6, m.getUserPhone());       // userPhone
	        pre.setString(7, m.getUserAddress1());    // userAddress1
	        pre.setString(8, m.getUserAddress2());    // userAddress2
	        pre.setString(9, m.getUserPost());        // userPost
	        pre.setString(10, m.getUserEmail());      // userEmail
	        pre.setString(11, m.getUserCategory());   // userCategory
	        
	        // userProfile 설정
	        if (m.getUserProfile() != null && !m.getUserProfile().trim().isEmpty()) {
	            pre.setString(12, m.getUserProfile()); // 사용자가 업로드한 파일명 사용
	        }
	     
	        result = pre.executeUpdate();
	        if (result == 1) {
	            System.out.println("회원정보 삽입 완료");
	        }

	    }catch (Exception e) {
	        e.printStackTrace();
	    }

	    return result;
	}

	public int getChNum(String userId) {
		String sql = "select chNum "
				   + "from channellist "
				   + "where ownerid = '" + userId + "'";
		
		try (Connection con = ds.getConnection();
			PreparedStatement pre = con.prepareStatement(sql);) {
				
			try (ResultSet rs = pre.executeQuery()) {
				if (rs.next()) {
					return rs.getInt("chNum");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	
	}

	public String getUserProfile(String userId) {
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String userProfileImg = null;

	    try {
	        con = ds.getConnection();
	        String sql = "SELECT userProfile FROM itda_user WHERE userid=?";
	        pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, userId);
	        
	        rs = pstmt.executeQuery();
	        
	        if (rs.next()) {
	            userProfileImg = rs.getString("userProfile");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (pstmt != null) {
	            try {
	                pstmt.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (con != null) {
	            try {
	                con.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    return userProfileImg;
	}
	
}
