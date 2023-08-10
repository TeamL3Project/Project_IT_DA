package Channel.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class ChannelDAO {

	private DataSource ds;
	int result = 0;
	public ChannelDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
		}
	}

    public List<ChannelBean> getChannelList() {
        List<ChannelBean> channelList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = ds.getConnection();
            String sql = "SELECT * FROM channellist where rownum <= 6 ";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                // 채널 데이터를 읽어와서 Channel 객체로 만들어서 리스트에 추가합니다.
                ChannelBean channel = new ChannelBean();
                channel.setChnum(rs.getInt("chNum"));
                channel.setOwnerid(rs.getString("ownerid"));
                channel.setChname(rs.getString("chName"));
                channel.setChprofile(rs.getString("chprofile"));
                channel.setChinfo(rs.getString("chinfo"));
                channel.setCate_id(rs.getInt("cate_id"));
                channel.setChfollow(rs.getInt("chFollow"));
                channel.setChopendate(rs.getTimestamp("chOpendate"));
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
    
    public List<ChannelBean> getChannelList(int category_id) {
        List<ChannelBean> channelList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = ds.getConnection();
            String sql = "SELECT * FROM channellist where category CATE_ID = ? and rownum <= 6 order by chvisit desc";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, category_id);
            
            rs = pstmt.executeQuery();

            while (rs.next()) {
                // 채널 데이터를 읽어와서 Channel 객체로 만들어서 리스트에 추가합니다.
                ChannelBean channel = new ChannelBean();
                channel.setChnum(rs.getInt("chNum"));
                channel.setOwnerid(rs.getString("ownerid"));
                channel.setChname(rs.getString("chName"));
                channel.setChprofile(rs.getString("chprofile"));
                channel.setChinfo(rs.getString("chinfo"));
                channel.setCate_id(rs.getInt("cate_id"));
                channel.setChfollow(rs.getInt("chFollow"));
                channel.setChopendate(rs.getTimestamp("chOpendate"));
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
	public int insertChOwner(ChannelBean ch, String userId) {	//판매회원 가입시 판매자 정보를 채널리스트 db에 삽입
		String sql = "insert into channelList "
				   + "values((select nvl(max(chNum), 0)+1 from channelList), ?, "
				   + "?, ?, ?, ?, 0, sysdate, 0) ";
		
		try(Connection con = ds.getConnection();
			PreparedStatement pre = con.prepareStatement(sql);) {
					
			pre.setString(1, userId);					//소유자 id
			pre.setString(2, ch.getChname());			//채널 이름
			pre.setString(3, ch.getChprofile());		//채널 프로필
			pre.setString(4, ch.getChinfo());			//채널소개
			pre.setInt(5, ch.getCate_id());				//카테고리 id
			
					
			result = pre.executeUpdate();									//삽입 성공시 1
			System.out.println("채널리스트 DB 삽입 성공");
		}catch (Exception e) {
			e.printStackTrace();
				
		}
			
		return result;
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

}