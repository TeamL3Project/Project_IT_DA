package channel.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

	public List<ChannelBean> getChannellist() {
		List<ChannelBean> channelList = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = ds.getConnection();
			String sql = "SELECT * FROM CHANNELLIST";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ChannelBean channel = new ChannelBean();
				channel.setChnum(rs.getInt("chnum"));
				channel.setOwnerid(rs.getString("ownerid"));
				channel.setChname(rs.getString("chname"));
				channel.setChprofile(rs.getString("chprofile"));
				channel.setChinfo(rs.getString("chinfo"));
				channel.setCate_id(rs.getInt("cate_id"));
				channel.setChfollow(rs.getInt("chfollow"));
				channel.setChopendate(rs.getInt("chopendate"));
				channel.setChvisit(rs.getInt("chvisit"));

				channelList.add(channel);
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

		return channelList;
	}
}
