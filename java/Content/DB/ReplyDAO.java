package Content.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ReplyDAO {
	private DataSource ds;
	
	public ReplyDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public int getListCount(int replyNum) {
		String sql = "select count(*) from boardreply "
				   + "where boardNum = ?";
		int c = 0;
		
		try (Connection con = ds.getConnection();
			PreparedStatement pre = con.prepareStatement(sql);) {
			
			pre.setInt(1, replyNum);
			
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

	
	
	public JsonArray getReplyList(int replyNum, int state) {
		String sort = "asc";
		
		if (state == 2) {
			sort = "desc";
		}
		
		String sql = "select replynum, replywriter, replycontent, replydate, replylev, replyseq, replyref "
				+ "from boardreply "
				+ "where replynum = ? "
				+ "order by replyref " + sort + ", replyseq asc ";
		
		JsonArray array = new JsonArray();
		
		try (Connection con = ds.getConnection();
			PreparedStatement pre = con.prepareStatement(sql)) {
			
			pre.setInt(1, replyNum);
			
			try (ResultSet rs = pre.executeQuery()) {
				while (rs.next()) {
					JsonObject obj = new JsonObject();
					
					obj.addProperty("replyNum", rs.getInt(1));
					obj.addProperty("replywriter", rs.getString(2));
					obj.addProperty("replycontent", rs.getString(3));
					obj.addProperty("replydate", rs.getString(4));
					obj.addProperty("replylev", rs.getInt(5));
					obj.addProperty("replyseq", rs.getInt(6));
					obj.addProperty("replyref", rs.getInt(7));
					
				}
			}
		
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return array;
		
	}

	public int replyDelete(int num) {
		String sql = "delete boardreply "
				   + "where boardNum = ?";
		int result = 0;
		
		try (Connection con = ds.getConnection();
			PreparedStatement pre = con.prepareStatement(sql);) {
			
			pre.setInt(1, num);
			
			result = pre.executeUpdate();
			
			if (result == 1) {
				System.out.println("댓글 삭제가 정상처리되었습니다.");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("replyDelete() 에러: " + e);
		}//pre try end
		
		return result;
		
	}

	public int replyInsert(Reply re) {
		String sql = "insert into boardreply "
				   + "values(RE_SEQ.nextval,?,?,?, "
				   + "RE_SEQ.nextval, 0, sysdate, NULL, 0) ";
		
		int result = 0;
		
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

	public int replyUpdate(Reply re) {
		String sql = "update boardreply "
				   + "set replycontent = ? "
				   + "where replynum = ?";
		
		int result = 0;
		
		try(Connection con = ds.getConnection();
			PreparedStatement pre = con.prepareStatement(sql);) {
					
			pre.setString(1, re.getReplyContent());
			pre.setInt(2, re.getReplyNum());
					
			result = pre.executeUpdate();						//삽입 성공시 1
				
		}catch (Exception e) {
			e.printStackTrace();
		}
				
		return result;
	}

	public int ReplyReply(Reply re) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
