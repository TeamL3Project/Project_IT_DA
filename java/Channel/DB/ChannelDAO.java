package Channel.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import controller.channel.Channel;

public class ChannelDAO {

    private DataSource ds;

    public List<Channel> getChannelList() {
        List<Channel> channelList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = ds.getConnection();
            String sql = "SELECT * FROM channellist";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                // 채널 데이터를 읽어와서 Channel 객체로 만들어서 리스트에 추가합니다.
                Channel channel = new Channel();
                channel.setChNum(rs.getInt("chNum"));
                channel.setOwnerid(rs.getString("ownerid"));
                channel.setChName(rs.getString("chName"));
                channel.setChprofile(rs.getString("chprofile"));
                channel.setChinfo(rs.getString("chinfo"));
                channel.setCate_id(rs.getInt("cate_id"));
                channel.setChFollow(rs.getInt("chFollow"));
                channel.setChOpendate(rs.getTimestamp("chOpendate"));
                channel.setChvisit(rs.getInt("chvisit"));
                channelList.add(channel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return channelList;
    }

	public Channel getChannelByChNum(int chNum) {
		// TODO Auto-generated method stub
		return null;
	}
}
