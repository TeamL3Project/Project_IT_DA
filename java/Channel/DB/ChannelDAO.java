package Channel.DB;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ChannelDAO {

	private DataSource ds;
	private int result = 0;
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
            String sql = "select * from (SELECT * FROM channellist order by chvisit desc) where rownum <= 6";
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
            String sql = "select * from (SELECT * FROM channellist WHERE cate_id = ? order by chvisit desc) where rownum <= 6";
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

	public ChannelBean getChannelDetail(int chnum) {
		ChannelBean channel = new ChannelBean();

		String sql = "SELECT * FROM CHANNELLIST WHERE CHNUM = ?";

		try (Connection con = ds.getConnection(); 
			 PreparedStatement pstmt = con.prepareStatement(sql);) {

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

	public int getUserCountById(String input_id) {
		   int userCount = 0;
		    Connection conn = null;
		    PreparedStatement pstmt = null;
		    ResultSet rs = null;

		    try {
		        conn = ds.getConnection();
		        String sql = "SELECT COUNT(*) as count FROM itda_user WHERE userid = ?";
		        pstmt = conn.prepareStatement(sql);

		        pstmt.setString(1, input_id);
		        rs = pstmt.executeQuery();

		        if (rs.next()) {
		            userCount = rs.getInt("count");
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

		    return userCount;
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

	public ChannelBean getSellergraph(int chnum) {
		ChannelBean channel = new ChannelBean();
        String sql = "SELECT * "
	        		+ "FROM channellist "
	        		+ "WHERE chNum = ?";

        try (Connection con = ds.getConnection(); 
   			 PreparedStatement pstmt = con.prepareStatement(sql);) {

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

	public ChannelBean getChannelDetails(String chNum) {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    ChannelBean channelDetails = null;

	    try {
	        conn = ds.getConnection();
	        String query = "SELECT * FROM channellist WHERE chnum = ?";
	        pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, chNum);
	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            channelDetails = new ChannelBean();
	            channelDetails.setChnum(rs.getInt("chnum"));
	            channelDetails.setOwnerid(rs.getString("ownerid"));
	            channelDetails.setChname(rs.getString("chname"));
	            channelDetails.setChprofile(rs.getString("chprofile"));
	            channelDetails.setChinfo(rs.getString("chinfo"));
	            channelDetails.setCate_id(rs.getInt("cate_id"));
	            channelDetails.setChfollow(rs.getInt("chfollow"));
	            channelDetails.setChopendate(rs.getTimestamp("chopendate"));
	            channelDetails.setChvisit(rs.getInt("chvisit"));
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

	    return channelDetails;
	}
	
	public List<ChannelBean> goMyChannelList(String id) {
		String sql = "SELECT chnum "
				+ "FROM channellist "
				+ "WHERE ownerid = ? ";
		List<ChannelBean> chlist = new ArrayList<>();
		
		try (Connection con = ds.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			
			pstmt.setString(1, id);
			
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					ChannelBean channel = new ChannelBean();
					channel.setChnum(rs.getInt("CHNUM"));
					chlist.add(channel);
				}
				
			} catch (NumberFormatException ex) {
				ex.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return chlist;
	}
	
}
