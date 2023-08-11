package ChannelCategory.DB;

import util.dbService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static util.dbService.*;

public class ChannelCategoryDAO {
	public ChannelCategoryDAO() {
		dbConntect();
	}

	public List<ChannelCategoryBean> selectInfo(){
		List<ChannelCategoryBean> ChannelCategoryList = new ArrayList<>();
		String query = "select * from chcategory";

		try(Connection con = dbService.ds.getConnection();
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery()){

			while(rs.next()){
				ChannelCategoryBean category = new ChannelCategoryBean();
				category.setCategoryId(rs.getInt("cate_id"));
				category.setCategoryName(rs.getString("cate_name"));
				ChannelCategoryList.add(category);
			}

		}catch (Exception ex){
			ex.printStackTrace();
		}
		return ChannelCategoryList;
	}


}
