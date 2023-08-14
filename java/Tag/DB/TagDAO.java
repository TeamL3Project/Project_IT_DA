package Tag.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import Content.DB.ContentBean;

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
	
	
	public boolean tagInsert(TagBean t) {
		int result = 0; // 초기값

		ContentBean co = new ContentBean();

		String sql = "INSERT INTO tag " 
				+ " (tagid, boardnum, tagname)"
				+ "	VALUES(tag_seq.nextval, ?, ?)";
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {

			// 새로운 글을 등록하는 부분입니다.
			pstmt.setInt(1, co.getBoardNum());
			pstmt.setString(2, t.getTagname());

			result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println("데이터 삽입이 모두 완료되었습니다.");
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("tagInset() 에러: " + ex);
		}
		return false;
	} // tagInset()메서드 end

}
