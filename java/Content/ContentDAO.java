package Content;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContentDAO {
    int result = 0;
    private DataSource ds;

    public ContentDAO(){
        try {
            Context init = new InitialContext();
            ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public Content contentSelect(int boardNum) {
        String query = "select * from chboard where boardnum = ?";
        Content co = new Content();
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




    public List<Content> popcontentSelect(){
        String query = "select * from (select * from chboard order by (boardheart + boardvisit) desc) where rownum <= 7";
        List<Content> contentList = new ArrayList<Content>();
        try(Connection conn = ds.getConnection();
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();){

            while(rs.next()){
                Content co = new Content();
                co.setBoardNum(rs.getInt(1));
                co.setChNum(rs.getInt(2));
                co.setWriter(rs.getString(3));
                co.setBoardTitle(rs.getString(4));
                co.setBoardContent(rs.getString(5));
                co.setThumbNail(rs.getString(13));
                contentList.add(co);
             }

        } catch (Exception ex){
            ex.printStackTrace();
        }
        return contentList;
    }

    public int contentInsert(Content co){
        String query = "insert into chboard values(bo_seq.nextval,?,?,?,?,0,?,'Y','Y',sysdate,'',0)";

        try(Connection con = ds.getConnection();
            PreparedStatement pst = con.prepareStatement(query)){

            pst.setInt(1,co.getChNum());
            pst.setString(2, co.getWriter());
            pst.setString(3, co.getBoardTitle());
            pst.setString(4, co.getBoardContent());
            pst.setInt(5, co.getChcate_id());

            result = pst.executeUpdate();
            if(result == 1){
                System.out.println("콘텐트 등록 완료");
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }





}
