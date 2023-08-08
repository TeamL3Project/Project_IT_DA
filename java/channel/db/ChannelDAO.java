package channel.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ChannelDAO {

	private DataSource ds;

	public ChannelDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
		}
	}

	public ChannelBean getChannellist(int chnum) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ChannelBean channel = new ChannelBean();

		try {
			conn = ds.getConnection();
			String sql = "SELECT * FROM CHANNELLIST WHERE CHNUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, chnum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				channel.setChnum(rs.getInt("CHNUM"));
				channel.setOwnerid(rs.getString("OWNERID"));
				channel.setChname(rs.getString("CHNAME"));
				channel.setChprofile(rs.getString("CHPROFILE"));
				channel.setChinfo(rs.getString("CHINFO"));
				channel.setCate_id(rs.getInt("CATE_ID"));
				channel.setChfollow(rs.getInt("CHFOLLOW"));
				channel.setChopendate(rs.getTimestamp("CHOPENDATE"));
				channel.setChvisit(rs.getInt("CHVISIT"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return channel;
	}

	public ChannelBean getChannelDetail(int chnum) {
		ChannelBean channel = null;
		String sql = "SELECT * FROM CHANNELLIST WHERE CHNUM = ?";

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setInt(1, chnum);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					channel.setChnum(rs.getInt("CHNUM"));
					channel.setOwnerid(rs.getString("OWNERID"));
					channel.setChname(rs.getString("CHNAME"));
					channel.setChprofile(rs.getString("CHPROFILE"));
					channel.setChinfo(rs.getString("CHINFO"));
					channel.setCate_id(rs.getInt("CATE_ID"));
					channel.setChfollow(rs.getInt("CHFOLLOW"));
					channel.setChopendate(rs.getTimestamp("CHOPENDATE"));
					channel.setChvisit(rs.getInt("CHVISIT"));

				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			System.out.println("getDeatil() 에러 :" + e);
		}
		return channel;
	}

	public void setReadCountUpdate(int num) {
		String sql = "UPDATE board SET BOARD_READCOUNT = BOARD_READCOUNT + 1 WHERE BOARD_NUM = ?";

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setInt(1, num);
			pstmt.executeUpdate();

		} catch (Exception se) {
			System.out.println("SetReadCountUpdate() 에러 :" + se);
		}
	}// serReadContUpdate() 메서드 end.
}
