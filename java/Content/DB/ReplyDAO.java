package Content.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ReplyDAO {
	private DataSource ds;
	private int result = 0;
	
	public ReplyDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public int getListCount(int boardNum) {
		String sql = "select count(*) from boardreply "
				   + "where boardNum = ?";
		int c = 0;
		
		try (Connection con = ds.getConnection();
			PreparedStatement pre = con.prepareStatement(sql);) {
			
			pre.setInt(1, boardNum);
			
			try (ResultSet rs = pre.executeQuery()) {
				if (rs.next()) {
					c = rs.getInt(1);
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		
		}
		
		return c;
	
	}

	
	
	public JsonArray getReplyList(int boardNum, int state) {
		String sort = "asc";
		
		if (state == 2) {
			sort = "desc";
		}
		
		String sql = "select replynum, replywriter, replycontent, replyref, replylev, replyseq, replydate "
				+ "from boardreply "
				+ "where boardnum = ? "
				+ "order by replyref " + sort + ", replyseq asc ";
		
		JsonArray array = new JsonArray();
		
		try (Connection con = ds.getConnection();
			PreparedStatement pre = con.prepareStatement(sql)) {
			
			pre.setInt(1, boardNum);
			
			try (ResultSet rs = pre.executeQuery()) {
				while (rs.next()) {
					JsonObject obj = new JsonObject();
					
					obj.addProperty("replyNum", rs.getInt(1));
					obj.addProperty("replywriter", rs.getString(2));
					obj.addProperty("replycontent", rs.getString(3));
					obj.addProperty("replyref", rs.getInt(4));
					obj.addProperty("replylev", rs.getInt(5));
					obj.addProperty("replyseq", rs.getInt(6));
					obj.addProperty("replydate", rs.getString(7));
					
					array.add(obj);
				}
			}
		
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return array;
		
	}

	public boolean replyDelete(int num) {					//댓글 삭제
		String sql = "select replyref, replylev, replyseq "
				   + "from boardreply "
				   + "where replynum = ?";				
		
		String sql2 = "delete from boardreply "
				    + "where replyref = ? "
				    + "and replylev >= ? "
				    + "and replyseq >= ? "
				    + "and replyseq <=(nvl((select min(replyseq)-1 "
				    + "					   	from boardreply "
				    + "					   	where replyref = ? "
				    + "					   	and replylev = ? "
				    + "					   	and replyseq > ?), (select max(replyseq) "
				    + "										   	from boardreply "
				    + "										   	where replyref = ?)"
				    + "					   ) "
				    + "				   )";
		
		boolean result = false;
		
		try (Connection con = ds.getConnection();
			PreparedStatement pre = con.prepareStatement(sql);) {
			
			pre.setInt(1, num);
			
			try(ResultSet rs = pre.executeQuery();){
				if (rs.next()) {
					try (PreparedStatement pre2 = con.prepareStatement(sql2)){
						pre2.setInt(1, rs.getInt("replyref"));
						pre2.setInt(2, rs.getInt("replylev"));
						pre2.setInt(3, rs.getInt("replyseq"));
						pre2.setInt(4, rs.getInt("replyref"));
						pre2.setInt(5, rs.getInt("replylev"));
						pre2.setInt(6, rs.getInt("replyseq"));
						pre2.setInt(7, rs.getInt("replyref"));
						
						int count = pre2.executeUpdate();
						if (count >= 1)
							result = true;
					}//pre2 end
				}//re end
			}//rs end
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("replyDelete() 에러: " + e);
		}//pre try end
		
		return result;
	}
	
	

	public int replyInsert(Reply re) {					//댓글 작성
		String sql = "insert into boardreply "
				   + "values(re_seq.nextval, ?, ?, ?, "
				   + "re_seq.nextval, 0, 0, sysdate, NULL) ";
		
		
		try(Connection con = ds.getConnection();
			PreparedStatement pre = con.prepareStatement(sql);) {
				
			pre.setInt(1, re.getBoardNum());			//게시물 번호
			pre.setString(2, re.getReplyWriter());		//댓글 작성자
			pre.setString(3, re.getReplyContent());		//내용
				
			result = pre.executeUpdate();				//삽입 성공시 1
			
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return result;
	}

	
	public int replyUpdate(Reply re) {					//댓글 수정
		String sql = "update boardreply "
				   + "set replycontent = ?, replyupdate = sysdate "
				   + "where replynum = ?";
		
		try(Connection con = ds.getConnection();
			PreparedStatement pre = con.prepareStatement(sql);) {
					
			pre.setString(1, re.getReplyContent());
			pre.setInt(2, re.getReplyNum());
					
			result = pre.executeUpdate();				//삽입 성공시 1
				
		}catch (Exception e) {
			e.printStackTrace();
		}
				
		return result;
	}

	public int ReplyReply(Reply re) {					//대댓글
		try (Connection con = ds.getConnection();){
			
			con.setAutoCommit(false);
			
			try {
				reply_update(con, re.getReplyref(), re.getReplyseq());
				result = reply_insert(con, re);
				con.commit();
				con.setAutoCommit(true);
			
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	private int reply_insert(Connection con, Reply re) {		//대댓글 작성
		String sql = "insert into boardreply "
				   + "values(re_seq.nextval, ?, ?, ?, "
				   + "?, ?, ?, sysdate, NULL)";
		
		try (PreparedStatement pre = con.prepareStatement(sql);) {
			pre.setInt(1, re.getBoardNum());
			pre.setString(2, re.getReplyWriter());
			pre.setString(3, re.getReplyContent());
			pre.setInt(4, re.getReplyref());
			pre.setInt(5, re.getReplylev() + 1);
			pre.setInt(6, re.getReplyseq() + 1);
			
			
			result = pre.executeUpdate();
			
			if (result == 1) {
				System.out.println("답글이 성공적으로 작성되었습니다.");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	
	private void reply_update(Connection con, int replyref, int replyseq) {		//대댓글 수정
		String sql = "update boardreply "
				   + "set replyseq = replyseq + 1 "
				   + "where replyref = ? "
				   + "and replyseq > ? ";
		
		try (PreparedStatement pre = con.prepareStatement(sql);) {
				
			pre.setInt(1, replyref);
			pre.setInt(2, replyseq);
			
			pre.executeUpdate();
			
		}catch (Exception e) {
				e.printStackTrace();
		}
		
		
	}

	public Reply getReplyCount(int channelnum) {
		Reply r = new Reply();
		String sql = "select COALESCE(b.cnt, 0) as cnt "
				+ "from chboard a "
				+ "left outer join (select boardnum, count(replynum) as cnt "
				+ "				 from boardreply "
				+ "				 group by boardnum) b "
				+ "on a.boardnum = b.boardnum "
				+ "where a.chnum = ? "
				+ "order by a.boardnum ";
		
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, channelnum);
			
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					r.setCnt(rs.getInt("cnt"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getListCount() 에러: " + ex);
		}

		return r;
	} // ReplyCount() end

	
}
