package Content.DB;

import Channel.DB.ChannelBean;
import util.dbService;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContentDAO {
	private int result = 0;
	private DataSource ds;
	private final int PAGE_LIMIT = 10;
	private final int FIRST_START_PAGE = 1;
	private static final int POPULAR_CONTENT_NUM = 7;
	public ContentDAO() {
		dbService.dbConntect();
	}

	private static List<ContentBean> setContetnInfo(PreparedStatement pst) throws Exception {
		List<ContentBean> contentList = new ArrayList<>();
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
				co.setBoardOpen(rs.getString("boardopen"));
				co.setBoardNore(rs.getString("boardNore"));
				co.setBoardDate(rs.getTimestamp("boardDate"));
				co.setBoardUpdate(rs.getTimestamp("boardUpdate"));
				co.setBoardVisit(rs.getInt("boardvisit"));
				co.setThumbNail(rs.getString("ThumbNail"));
				co.setIntro(rs.getString("intro"));

				contentList.add(co);
			}
		}
		return contentList;
	}
	public ContentBean contentSelect(int boardNum) {
		// String query = "select * from chboard where boardnum = ?";
		String query = "select chboard.*, chboardcategory.chcate_name " 
				+ "from chboard   join chboardcategory "
				+ "on   chboard.chnum  =  chboardcategory.chnum "
				+ "where boardnum = ?  "
				+ "and chboard.chcate_id = chboardcategory.chcate_id ";
		ContentBean co = new ContentBean();
		try (Connection con = dbService.ds.getConnection(); PreparedStatement pst = con.prepareStatement(query);) {

			pst.setInt(1, boardNum);
			try (ResultSet rs = pst.executeQuery();) {

				if (rs.next()) {
					co.setBoardNum(rs.getInt("boardnum"));
					co.setChNum(rs.getInt("chnum"));
					co.setWriter(rs.getString("writer"));
					co.setBoardTitle(rs.getString("boardtitle"));
					co.setBoardHeart(rs.getInt("boardheart"));
					co.setChcate_id(rs.getInt("chcate_id"));
					co.setBoardDate(rs.getTimestamp("boarddate"));
					co.setBoardVisit(rs.getInt("boardvisit"));
					co.setThumbNail(rs.getString("thumbnail"));
					co.setBoardContent(rs.getString("boardcontent"));
					co.setChcate_name(rs.getString("chcate_name"));
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return co;
	}

	public List<ContentBean> newContentSelect(int channelNum) {
		String query = "select * from (select * from chboard where chnum = ? order by boardupdate desc) where rownum = 1";
		List<ContentBean> contentList = new ArrayList<>();
		try (Connection conn = dbService.ds.getConnection();
			 PreparedStatement pst = conn.prepareStatement(query);) {
			pst.setInt(1,channelNum);
			contentList = setContetnInfo(pst);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return contentList;
	}
	public List<ContentBean> popcontentSelect() {
		String query = "select * from (select * from chboard order by (boardheart + boardvisit) desc) where rownum <= "+POPULAR_CONTENT_NUM;
		List<ContentBean> contentList = new ArrayList<>();
		try (Connection conn = dbService.ds.getConnection();
			 PreparedStatement pst = conn.prepareStatement(query);) {
			contentList = setContetnInfo(pst);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return contentList;
	}

	public List<ContentBean> contentSelectBycategory(int pageCount) {
		String query = "select * from (select rownum r, chboard.* from CHBOARD join CHANNELLIST on CHBOARD.CHNUM = CHANNELLIST.CHNUM order by BOARDNUM desc) where r between ? and ?";
		int startRow = (pageCount - FIRST_START_PAGE) * PAGE_LIMIT + FIRST_START_PAGE;
		int endRow = pageCount * PAGE_LIMIT;
		List<ContentBean> contentSelectBycategory = new ArrayList<>();
		try (Connection conn = dbService.ds.getConnection(); PreparedStatement pst = conn.prepareStatement(query);) {

			pst.setInt(1, startRow);
			pst.setInt(2, endRow);
			contentSelectBycategory = setContetnInfo(pst);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return contentSelectBycategory;
	}

	public List<ContentBean> contentSelectBycategory(int channelCategoryId, int pageCount) {
		String query = "select * from (select rownum r, chboard.* from CHBOARD join CHANNELLIST on CHBOARD.CHNUM = CHANNELLIST.CHNUM where CATE_ID = ? order by BOARDNUM desc) where r between ? and ?";
		int startRow = (pageCount - FIRST_START_PAGE) * PAGE_LIMIT + FIRST_START_PAGE;
		int endRow = pageCount * PAGE_LIMIT;
		List<ContentBean> contentSelectBycategory = new ArrayList<>();

		try (Connection conn = dbService.ds.getConnection(); PreparedStatement pst = conn.prepareStatement(query);) {

			pst.setInt(1, channelCategoryId);
			pst.setInt(2, startRow);
			pst.setInt(3, endRow);
			contentSelectBycategory = setContetnInfo(pst);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return contentSelectBycategory;
	}

	public int contentInsert(ContentBean co) {
		String query = "insert into chboard values(bo_seq.nextval,?,?,?,0,?,'Y','Y',systimestamp,'',0,?,?,?)";
		try (Connection con = dbService.ds.getConnection(); PreparedStatement pst = con.prepareStatement(query)) {
				Clob clobContent = con.createClob();
				clobContent.setString(1, co.getBoardContent());
			Clob clobIntro = con.createClob();
				clobIntro.setString(1, co.getIntro());
			pst.setInt(1, co.getChNum());
			pst.setString(2, co.getWriter());
			pst.setString(3, co.getBoardTitle());
			pst.setInt(4, co.getChcate_id());
			pst.setString(5, co.getThumbNail());
			pst.setClob(6, clobIntro);
			pst.setClob(7, clobContent);
			pst.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public int getListCount() {
		String sql = "select count(*) from chboard";
		int x = 0;
		try (Connection con = dbService.ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

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

		try (Connection con = dbService.ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
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

	public List<ContentBean> channelhomeSelect(int chNum) {
	    String query = "SELECT * FROM (SELECT * FROM chboard WHERE chNum = ? ORDER BY boardvisit DESC) WHERE ROWNUM <= 6";
	    List<ContentBean> contentList = new ArrayList<>();

	    try (Connection con = dbService.ds.getConnection();
	         PreparedStatement pstmt = con.prepareStatement(query)) {
	        
	        pstmt.setInt(1, chNum); 
	        
	        try (ResultSet rs = pstmt.executeQuery()) {
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
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    } catch (Exception ex) {
	        ex.printStackTrace();
	        System.out.println("channelhomeSelect() 에러: " + ex); 
	    }
	    return contentList;
	}


	// 게시물 목록을 가져오는 메소드입니다.
	public List<ContentBean> getBoardListByBoardNum(int chnum) {
		List<ContentBean> contentList = new ArrayList<>();

		String sql = "select * " + "from (SELECT * from chboard " + "	     where chnum = ?"
				+ "	     order by boardnum desc)" + "		 where rownum <= 5";

		try (Connection conn = dbService.ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {

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
		try (Connection con = dbService.ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("setReadiCountUpdate() 에러: " + ex);
		}
	} // setReadCountUpdate() 메서드 end

	public ContentBean getDetail(int num) {
		ContentBean co = null;
		String sql = "select * from chboard where BOARDNUM = ?";
		try (Connection con = dbService.ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {

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


	public int getListCount(int chnum, int chcate_id) {
		String sql = "select count(*) from chboard "
				+ "where chNum = ? "
				+ "AND chcate_id = ? ";
		int x = 0;
		try (Connection con = dbService.ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, chnum);
			pstmt.setInt(2, chcate_id);

			try (ResultSet rs = pstmt.executeQuery()) {
				if(rs.next()) {
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
	

	
	public int plusheartCount(int boardNum) {
		String sql = "update chboard "
				   + "set boardheart = boardheart + 1 "
				   + "where boardNum = ? ";
		
		try (Connection con = dbService.ds.getConnection();
			PreparedStatement pre = con.prepareStatement(sql);) {
			
			pre.setInt(1, boardNum);
			int rowsUpdated = pre.executeUpdate();
			
			if (rowsUpdated > 0) {
		            return getUpdatedHeartCount(con, boardNum);
		    }
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	
	private int getUpdatedHeartCount(Connection con, int boardNum) {
		String sql = "select boardheart "
				   + "from chboard "
				   + "where boardNum = ?";
		
		try (PreparedStatement pre = con.prepareStatement(sql)) {
			pre.setInt(1, boardNum);
			
			try (ResultSet rs = pre.executeQuery()) {
				if (rs.next()) {
					return rs.getInt("boardheart");
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public int previousValue(int boardNum) {
		String sql = "update chboard "
				   + "set boardheart = boardheart - 1 "
				   + "where boardNum = ? ";
		
		try (Connection con = dbService.ds.getConnection();
			PreparedStatement pre = con.prepareStatement(sql);) {
			
			pre.setInt(1, boardNum);
			int rowsUpdated = pre.executeUpdate();
			
			if (rowsUpdated > 0) {
		            return getUpdatedHeartCount(con, boardNum);
		    }
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	

	public List<ContentBean> getAllchcatedata(int channelnum, String order, int page, int limit) {
		String query = "SELECT * "
				+ "from (select rownum rnum, j.* "
				+ "	 from(select chboard.*, nvl(cnt,0) cnt, "
				+ "			(select chcate_name from  chboardcategory c where c.chcate_id=chboard.chcate_id)  chcate_name"
				+ "		     from chboard left join  (select boardnum, count(*) cnt"
				+ "					     			from boardreply"
				+ "					      			group by boardnum "
				+ "                      ) f"
				+ "          on chboard.boardnum = f.boardnum "
				+ "          where chboard.chnum = ? "
				+ "          order by boardDate " + order + ", chboard.boardnum desc"
				+ "          ) j "
				+ "      where rownum <= ? "
				+ "     ) "
				+ "where rnum >= ? and rnum <= ? ";
		List<ContentBean> contentList = new ArrayList<>();
		// 한 페이지당 10개씩 목록인 경우 1페이지, 2페이지, 3페이지, 4페이지 ...
		int startrow = (page - 1) * limit + 1; //읽기 시작할 row 번호(1 11 21 31 ...
		int endrow = startrow + limit - 1; // 읽을 마지막 row 번호(10 20 30 40 ...

		 System.out.println(query);
		
		try (Connection conn = dbService.ds.getConnection();
			PreparedStatement pst = conn.prepareStatement(query);) {

			pst.setInt(1, channelnum);
			pst.setInt(2, endrow);
			pst.setInt(3, startrow);
			pst.setInt(4, endrow);

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
					co.setChcate_name(rs.getString("chcate_name"));
					co.setBoardOpen(rs.getString("boardOpen"));
					co.setBoardNore(rs.getString("boardNore"));
					co.setBoardDate(rs.getTimestamp("boardDate"));
					co.setBoardUpdate(rs.getTimestamp("boardUpdate"));
					co.setThumbNail(rs.getString("ThumbNail"));
					co.setCnt(rs.getInt("cnt"));
					contentList.add(co);

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return contentList;
	}

	public List<ContentBean> getchcatedata(int chNum, int categoryId, int page, int limit) {
		String query = "SELECT * "
				+ "from (select rownum rnum, j.* "
				+ "			from(select * "
				+ "				FROM chboard natural join chboardcategory "
				+ "				WHERE chnum = ? "
				+ "				and chcate_id = ? ) j "
				+ "where rownum <= ? "
				+ ")"
				+ "where rnum >= ? and rnum <= ? "
				+ "order by boardDate asc";
		List<ContentBean> contentList = new ArrayList<>();

		try (Connection conn = dbService.ds.getConnection();
			PreparedStatement pst = conn.prepareStatement(query);) {
			
			int startrow = (page - 1) * limit + 1; //읽기 시작할 row 번호(1 11 21 31 ...
			int endrow = startrow + limit - 1; // 읽을 마지막 row 번호(10 20 30 40 ...
			
			 System.out.println(query);

			pst.setInt(1, chNum);
			pst.setInt(2, categoryId);
			pst.setInt(3, endrow);
			pst.setInt(4, startrow);
			pst.setInt(5, endrow);

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
					co.setChcate_name(rs.getString("chcate_name"));
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

	public int getListAllCount(int channelnum) {
		String sql = "select count(*) from chboard "
				+ "where chNum = ? ";
		int x = 0;
		try (Connection con = dbService.ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, channelnum);

			try (ResultSet rs = pstmt.executeQuery()) {
				if(rs.next()) {
					 x = rs.getInt(1);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getAllListCount() 에러: " + ex);
		}

		return x;
	} // getListAllCount() end

}
