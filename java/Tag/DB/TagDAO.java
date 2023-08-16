package Tag.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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



	public List<TagBean> getTagNameList(int boardNum, int chnum) {
		String sql = "select tag.tagname "
				+ "from chboard join tag "
				+ "on chboard.boardnum = tag.boardnum "
				+ "where chboard.chnum = ? and chboard.boardnum = ? ";
		
		List<TagBean> tlist = new ArrayList<>();
		try (Connection conn = ds.getConnection(); 
				PreparedStatement pst = conn.prepareStatement(sql);) {

			pst.setInt(1, boardNum);
			pst.setInt(2, chnum);
			try (ResultSet rs = pst.executeQuery();) {
				while (rs.next()) {
					TagBean t = new TagBean();
					tlist.add(t);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
				
		return tlist;
	}

}
