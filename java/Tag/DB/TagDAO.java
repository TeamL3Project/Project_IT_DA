package Tag.DB;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class TagDAO {
	
	private DataSource ds;
	private int result = 0;
	public TagDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
		}
	}

	public boolean tagInsert(String tag, int channelNumber) {
		String sql = "INSERT INTO tag "
				+ " (tagid, boardnum, tagname)"
				+ "	VALUES(tag_seq.nextval, ?, ?)";
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, channelNumber);
			pstmt.setString(2, tag);
			pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	} // tagInset()메서드 end
//보류
//	public TagBean tagSelect (int contentNum){
//		TagBean tag;
//		String query = "select * from tag where boardnum = ?";
//
//		try(Connection con = ds.getConnection();
//			PreparedStatement pst = con.prepareStatement(query);) {
//
//			pst.setInt(1, contentNum);
//
////			if()
//		}catch (Exception ex){
//			ex.printStackTrace();
//		}
//
//		return tag;
//	}


}
