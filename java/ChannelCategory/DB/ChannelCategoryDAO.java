package ChannelCategory.DB;

import static util.dbService.dbConntect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Channel.DB.ChannelBean;
import util.dbService;

public class ChannelCategoryDAO {
	public ChannelCategoryDAO() {
		dbConntect();
	}

	public List<ChannelCategoryBean> selectInfo() {
		List<ChannelCategoryBean> ChannelCategoryList = new ArrayList<>();
		String query = "select * from chcategory";

		try (Connection con = dbService.ds.getConnection();
				PreparedStatement pst = con.prepareStatement(query);
				ResultSet rs = pst.executeQuery()) {

			while (rs.next()) {
				ChannelCategoryBean category = new ChannelCategoryBean();
				category.setCategoryId(rs.getInt("cate_id"));
				category.setCategoryName(rs.getString("cate_name"));
				ChannelCategoryList.add(category);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ChannelCategoryList;
	}

	public ChannelCategoryBean getchTotaldata(int chNum) {
	    ChannelCategoryBean chcatedata = new ChannelCategoryBean();
	   
	    String sql;
	    if (chNum == 0) {
	        sql = "select * from CHBOARDCATEGORY order by chcate_id asc";
	    } else {
	        sql = "select * from CHBOARDCATEGORY where chNum = ? order by chcate_id asc";
	    }
	    
	    try (Connection con = dbService.ds.getConnection();
	         PreparedStatement pstmt = con.prepareStatement(sql);) {

	        if (chNum != 0) {
	            pstmt.setInt(1, chNum);
	        }

	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                chcatedata.setCategoryId(rs.getInt("CHCATE_ID"));
	                chcatedata.setChNum(rs.getInt("CHNUM"));
	                chcatedata.setCategoryName(rs.getString("CHATE_NAME"));
	            }
	        }
	    } catch (SQLException se) {
	        se.printStackTrace();
	    } catch (Exception e) {
	        System.out.println("getchTotaldata() 에러: " + e);
	    }
	    
	    return chcatedata;
	}


}
