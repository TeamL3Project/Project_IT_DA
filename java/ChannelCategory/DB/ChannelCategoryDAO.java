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

	public ChannelCategoryBean getchcatedata(int chateId, int chNum) {
		ChannelCategoryBean chcatedata = new ChannelCategoryBean();

		String sql = "SELECT * FROM chboardcategory WHERE chcate_id = ? AND chNum = ?";

		try (Connection con = dbService.ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setInt(1, chateId);
			pstmt.setInt(2, chNum);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					chcatedata.setCategoryId(rs.getInt("CATE_ID"));
					chcatedata.setChNum(rs.getInt("CHNUM"));
					chcatedata.setCategoryName(rs.getString("CHCATE_NAME"));

				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			System.out.println("getChcateData() 에러: " + e);
		}
		return chcatedata;
	}
}
