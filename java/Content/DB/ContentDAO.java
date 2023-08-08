package Content.DB;

import javax.naming.*;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContentDAO {
    private int result = 0;
    private DataSource ds;
    private final int PAGE_LIMIT = 10;
    private final int FIRST_START_PAGE = 1;

    public ContentDAO() {
        try {
            Context init = new InitialContext();
            ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ContentBean contentSelect(int boardNum) {
        String query = "select * from chboard where boardnum = ?";
        ContentBean co = new ContentBean();
        try (Connection con = ds.getConnection();
             PreparedStatement pst = con.prepareStatement(query);) {

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
                    co.setThumbNail(rs.getString(13));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return co;

    }


    public List<ContentBean> popcontentSelect() {
        String query = "select * from (select * from chboard order by (boardheart + boardvisit) desc) where rownum <= 7";
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
        int startRow = ((pageCount - FIRST_START_PAGE) / PAGE_LIMIT) * PAGE_LIMIT + FIRST_START_PAGE;
        int endRow = pageCount * PAGE_LIMIT;
        List<ContentBean> contentSelectBycategory = new ArrayList<>();

        try (Connection conn = ds.getConnection();
             PreparedStatement pst = conn.prepareStatement(query);) {

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
                    co.setThumbNail(rs.getString("ThumbNail"));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return contentSelectBycategory;
    }

    public List<ContentBean> contentSelectBycategory(int channelCategoryId, int pageCount) {
        String query = "select * from (select * from (select rownum r, CHBOARD.* from chboard where chcate_id = ? order by boardnum desc) where r between ? and ?)";
        int startRow = ((pageCount - FIRST_START_PAGE) / PAGE_LIMIT) * PAGE_LIMIT + FIRST_START_PAGE;
        int endRow = pageCount * PAGE_LIMIT;

        List<ContentBean> contentSelectBycategory = new ArrayList<>();

        try (Connection conn = ds.getConnection();
             PreparedStatement pst = conn.prepareStatement(query);) {

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

        try (Connection con = ds.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

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


}
