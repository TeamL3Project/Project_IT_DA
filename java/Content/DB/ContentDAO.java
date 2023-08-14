package Content.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import Channel.DB.ChannelBean;

public class ContentDAO {
	private int result = 0;
	private DataSource ds;
	private final int PAGE_LIMIT = 10;
	private final int FIRST_START_PAGE = 1;
	private final int POPULAR_CONTENT_NUM = 7;

	public ContentDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public ContentBean contentSelect(int boardNum) {
		// String query = "select * from chboard where boardnum = ?";
		String query = "select chboard.*, chboardcategory.chcate_name " + "from chboard   join chboardcategory "
				+ "on   chboard.chnum  =  chboardcategory.chnum " + "where boardnum = ?  "
				+ "and chboard.chcate_id = chboardcategory.chcate_id ";
		ContentBean co = new ContentBean();
		try (Connection con = ds.getConnection(); PreparedStatement pst = con.prepareStatement(query);) {

			pst.setInt(1, boardNum);
			try (ResultSet rs = pst.executeQuery();) {

				if (rs.next()) {
					co.setBoardNum(rs.getInt(1));
					co.setChNum(rs.getInt(2));
					co.setWriter(rs.getString(3));
					co.setBoardTitle(rs.getString(4));
					co.setBoardContent(rs.getString(5));
					co.setBoardHeart(rs.getInt(6));
					co.setChcate_id(rs.getInt(7));
					co.setBoardDate(rs.getTimestamp(10));
					co.setBoardVisit(rs.getInt(12));
					co.setThumbNail(rs.getString(13));
					co.setChcate_name(rs.getString(14));
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return co;

	}

	public List<ContentBean> popcontentSelect() {
		String query = "select * from (select * from chboard order by (boardheart + boardvisit) desc) where rownum <= "+POPULAR_CONTENT_NUM;
		List<ContentBean> contentList = new ArrayList<>();
		try (Connection conn = ds.getConnection();
				PreparedStatement pst = conn.prepareStatement(query);
				ResultSet rs = pst.executeQuery();) {

			while (rs.next()) {
				ContentBean co = new ContentBean();
				co.setBoardNum(rs.getInt(1));
				co.setChNum(rs.getInt(2));
				co.setWriter(rs.getString(3));
				co.setBoardTitle(rs.getString(4));
				co.setBoardContent(rs.getString(5));
				co.setThumbNail(rs.getString(13));
				contentList.add(co);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return contentList;
	}

	public List<ContentBean> contentSelectBycategory(int pageCount) {
		String query = "select * from (select * from (select rownum r, CHBOARD.* from chboard order by boardnum desc) where r between ? and ?)";
		int startRow = (pageCount - FIRST_START_PAGE) * PAGE_LIMIT + FIRST_START_PAGE;
		int endRow = pageCount * PAGE_LIMIT;
		List<ContentBean> contentSelectBycategory = new ArrayList<>();
		try (Connection conn = ds.getConnection(); PreparedStatement pst = conn.prepareStatement(query);) {

			pst.setInt(1, startRow);
			pst.setInt(2, endRow);
			try (ResultSet rs = pst.executeQuery();) {
				while (rs.next()) {
					ContentBean co = new ContentBean();
					co.setBoardNum(rs.getInt("boardnum"));
					co.setChNum(rs.getInt("ChNum"));
					co.setWriter(rs.getString("Writer"));
					co.setBoardTitle(rs.getString("BoardTitle"));
					co.setBoardContent(rs.getString("BoardContent"));
					co.setBoardHeart(rs.getInt("boardHeart"));
					co.setChCate_id(rs.getInt("chCate_id"));
					co.setBoardOpen(rs.getString("boardOpen"));
					co.setBoardNore(rs.getString("boardNore"));
					co.setBoardDate(rs.getTimestamp("boardDate"));
					co.setBoardUpdate(rs.getTimestamp("boardUpdate"));
					co.setThumbNail(rs.getString("ThumbNail"));
					contentSelectBycategory.add(co);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return contentSelectBycategory;
	}

	public List<ContentBean> contentSelectBycategory(int channelCategoryId, int pageCount) {
		String query = "select * from (select * from (select rownum r, CHBOARD.* from chboard where chcate_id = ? order by boardnum desc) where r between ? and ?)";
		int startRow = (pageCount - FIRST_START_PAGE) * PAGE_LIMIT + FIRST_START_PAGE;
		int endRow = pageCount * PAGE_LIMIT;
		List<ContentBean> contentSelectBycategory = new ArrayList<>();

		try (Connection conn = ds.getConnection(); PreparedStatement pst = conn.prepareStatement(query);) {

			pst.setInt(1, channelCategoryId);
			pst.setInt(2, startRow);
			pst.setInt(3, endRow);

			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					ContentBean co = new ContentBean();
					co.setBoardNum(rs.getInt("boardnum"));
					co.setChNum(rs.getInt("ChNum"));
					co.setWriter(rs.getString("Writer"));
					co.setBoardTitle(rs.getString("BoardTitle"));
					co.setBoardContent(rs.getString("BoardContent"));
					co.setBoardHeart(rs.getInt("boardHeart"));
					co.setChCate_id(rs.getInt("chCate_id"));
					co.setBoardOpen(rs.getString("boardOpen"));
					co.setBoardNore(rs.getString("boardNore"));
					co.setBoardDate(rs.getTimestamp("boardDate"));
					co.setBoardUpdate(rs.getTimestamp("boardUpdate"));
					co.setThumbNail(rs.getString("ThumbNail"));
					contentSelectBycategory.add(co);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return contentSelectBycategory;
	}

	public int contentInsert(ContentBean co) {
		String query = "insert into chboard values(bo_seq.nextval,?,?,?,?,0,?,'Y','Y',sysdate,'',0)";

		try (Connection con = ds.getConnection(); PreparedStatement pst = con.prepareStatement(query)) {

			pst.setInt(1, co.getChNum());
			pst.setString(2, co.getWriter());
			pst.setString(3, co.getBoardTitle());
			pst.setString(4, co.getBoardContent());
			pst.setInt(5, co.getChcate_id());

			result = pst.executeUpdate();
			if (result == 1) {
				System.out.println("콘텐트 등록 완료");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public int getListCount() {
		String sql = "select count(*) from chboard";
		int x = 0;
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					x = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getListCount() 에러: " + ex);
		}

		return x;
	} // getListCount() end

	public List<ContentBean> getContentList(int page, int limit) {
		// page : 페이지
		// limit : 페이지 당 목록의 수
		// board_re_ref desc, board_re_seq asc에 의해 정렬한 것을
		// 조건절에 맞는 rnum의 범위 만큼 가져오는 쿼리문입니다.

		String sql = "select * " + "from channellist " + "	join chboard " + "	on	c.chnum = b.chnum "
				+ "	join chboardcategory " + "	on	b.chnum = t.chnum " + "	where t.chcate_name = ? "
				+ "	and b.chcate_id = t.chcate_id;";

		List<ContentBean> list = new ArrayList<ContentBean>();
		// 한 페이지당 10개씩 목록인 경우 1페이지, 2페이지, 3페이지, 4페이지 ...
//		int startrow = (page - 1) * limit + 1; // 읽기 시작할 row 번호(1 11 21 31 ...
//		int endrow = startrow + limit - 1; // 읽을 마지막 row 번호(10 20 30 40 ...

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
//					
			try (ResultSet rs = pstmt.executeQuery()) {
				pstmt.setString(1, "CHCATE_NAME");
				// DB에서 가져온 데이터를 BoardBean에 담습니다.
				while (rs.next()) {
					ContentBean co = new ContentBean();
					co.setChNum(rs.getInt("CHNUM"));
					co.setChname(rs.getString("CHNAME"));
					co.setBoardNum(rs.getInt("BOARDNUM"));
					co.setChNum(rs.getInt("CHNUM"));
					co.setWriter(rs.getString("WRITER"));
					co.setBoardTitle(rs.getString("BOARDTITLE"));
					co.setBoardContent(rs.getString("BOARDCONTENT"));
					co.setBoardHeart(rs.getInt("BOARDHEART"));
					co.setBoardOpen(rs.getString("BOARDOPEN"));
					co.setBoardNore(rs.getString("BOARDNORE"));
					co.setBoardDate(rs.getTimestamp("BOARDDATE"));
					co.setThumbNail(rs.getString("THUMBNAIL"));
					co.setChcate_name(rs.getString("CHBOARD_NAME"));
					list.add(co); // 값을 담은 객체를 리스트에 저장합니다.
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getContentList() 에러: " + ex);
		}
		return list;
		// getContentList() end

	}

	public List<ContentBean> channelhomeSelect() {
		String query = "select * from (select * from chboard order by boardvisit desc) where rownum <= 6";
		List<ContentBean> contentList = new ArrayList<>();

		try (Connection conn = ds.getConnection();
				PreparedStatement pst = conn.prepareStatement(query);
				ResultSet rs = pst.executeQuery();) {

			while (rs.next()) {
				ContentBean co = new ContentBean();
				co.setBoardNum(rs.getInt(1));
				co.setChNum(rs.getInt(2));
				co.setWriter(rs.getString(3));
				co.setBoardTitle(rs.getString(4));
				co.setBoardContent(rs.getString(5));
				co.setThumbNail(rs.getString(13));
				contentList.add(co);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return contentList;
	}

	// 게시물 목록을 가져오는 메소드입니다.
	public List<ContentBean> getBoardListByBoardNum(int chnum) {
		List<ContentBean> contentList = new ArrayList<>();

		String sql = "select * " + "from (SELECT * from chboard " + "	     where chnum = ?"
				+ "	     order by boardnum desc)" + "		 where rownum <= 5";

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {

			pstmt.setInt(1, chnum);
			try (ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					ContentBean co = new ContentBean();
					co.setBoardNum(rs.getInt(1));
					co.setChNum(rs.getInt(2));
					co.setWriter(rs.getString(3));
					co.setBoardTitle(rs.getString(4));
					co.setBoardContent(rs.getString(5));
					co.setThumbNail(rs.getString(13));
					contentList.add(co);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return contentList;
	}

	public void setReadCountUpdate(int num) {
		String sql = "update chboard " + "set boardVisit = boardVisit + 1 " + "where boardNum = ?";
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("setReadiCountUpdate() 에러: " + ex);
		}
	} // setReadCountUpdate() 메서드 end

	public ContentBean getDetail(int num) {
		ContentBean co = null;
		String sql = "select * from chboard where BOARDNUM = ?";
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setInt(1, num);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					co = new ContentBean();
					co.setBoardNum(rs.getInt("BOARDNUM"));
					co.setChNum(rs.getInt("CHNUM"));
					co.setWriter(rs.getString("WRITER"));
					co.setBoardTitle(rs.getString("BOARDTITLE"));
					co.setBoardContent(rs.getString("BOARDCONTENT"));
					co.setBoardHeart(rs.getInt("BOARDHEART"));
					co.setBoardOpen(rs.getString("BOARDOPEN"));
					co.setBoardNore(rs.getString("BOARDNORE"));
					co.setBoardDate(rs.getTimestamp("BOARDDATE"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			System.out.println("getDetail() 에러: " + ex);
		}
		return co;
	} // getDetail() 메서드 end

	public boolean contentInset(ContentBean co) {

		int result = 0; // 초기값

		ChannelBean chdata = new ChannelBean();

		String sql = "INSERT INTO chboard " + " (boardNum, chNum, chCate_id, writer,"
				+ " boardTitle, boardContent, boardOpen, boardNore, thumbNail)" + "	VALUES(bo_seq.nextval, ?, ?, ?,"
				+ "			?, Y, Y, ?, ?)";
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {

			// 새로운 글을 등록하는 부분입니다.
			pstmt.setString(1, co.getBoardTitle());
			pstmt.setInt(2, chdata.getChnum());
			pstmt.setInt(3, chdata.getCate_id());
			pstmt.setString(4, co.getWriter());
			pstmt.setString(5, co.getBoardContent());
			pstmt.setString(6, co.getThumbNail());

			result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println("데이터 삽입이 모두 완료되었습니다.");
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("boardInset() 에러: " + ex);
		}
		return false;
	} // boardInset()메서드 end

	public List<ContentBean> getchcatedata(int chNum, int categoryId) {
			String query = "SELECT * FROM content WHERE chNum = ? AND chcate_id = ?";
			List<ContentBean> contentList = new ArrayList<>();
	
			try (Connection conn = ds.getConnection(); 
				PreparedStatement pst = conn.prepareStatement(query);) {
	
				pst.setInt(1, chNum);
				pst.setInt(2, categoryId);
	
				try (ResultSet rs = pst.executeQuery()) {
	
					while (rs.next()) {
						ContentBean co = new ContentBean();
						co.setBoardNum(rs.getInt("boardnum"));
						co.setChNum(rs.getInt("ChNum"));
						co.setWriter(rs.getString("Writer"));
						co.setBoardTitle(rs.getString("BoardTitle"));
						co.setBoardContent(rs.getString("BoardContent"));
						co.setBoardHeart(rs.getInt("boardHeart"));
						co.setChCate_id(rs.getInt("chCate_id"));
						co.setBoardOpen(rs.getString("boardOpen"));
						co.setBoardNore(rs.getString("boardNore"));
						co.setBoardDate(rs.getTimestamp("boardDate"));
						co.setBoardUpdate(rs.getTimestamp("boardUpdate"));
						co.setThumbNail(rs.getString("ThumbNail"));
						contentList.add(co);

					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return contentList;
		}
}

