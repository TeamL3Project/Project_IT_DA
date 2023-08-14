package ContentCategory.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class ContentCategoryDAO {

	private DataSource ds;
	int result = 0;

	public ContentCategoryDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
		}
	}

	public ContentCategoryBean getCategoryList() {

		return null;
	}

	public List<ContentCategoryBean> chcategorySelect(int chnum) {
		String sql = "select * from chboardcategory where chnum = ? order by chcate_name";
		List<ContentCategoryBean> ContentCategoryList = new ArrayList<>();

		try (Connection conn = ds.getConnection(); 
			PreparedStatement pst = conn.prepareStatement(sql);) {

			pst.setInt(1, chnum);

			try (ResultSet rs = pst.executeQuery();) {
				while (rs.next()) {
					ContentCategoryBean ca = new ContentCategoryBean();
					ca.setChcate_id(rs.getInt(1));
					ca.setChNum(rs.getInt(2));
					ca.setChcate_Name(rs.getString(3));
					ContentCategoryList.add(ca);
				}
				
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ContentCategoryList;

	}

		public List<ContentCategoryBean> getCategoryNameList(String chcate_name) {
			String sql = "select * from chboardcategory where chcate_name = ?";
			List<ContentCategoryBean> ContentCategoryList = new ArrayList<>();

			try (Connection conn = ds.getConnection();
					PreparedStatement pst = conn.prepareStatement(sql);){
					pst.setString(1, chcate_name);
					try(ResultSet rs = pst.executeQuery()) {
					
				while (rs.next()) {
					ContentCategoryBean ca = new ContentCategoryBean();
					ca.setChcate_id(rs.getInt(1));
					ca.setChNum(rs.getInt(2));
					ca.setChcate_Name(rs.getString(3));
					ContentCategoryList.add(ca);
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return ContentCategoryList;
		}
		
		public List<ContentCategoryBean> getCategoryNameList(int chnum) {
			String sql = "select chcate_name "
					+ "from chboardcategory "
					+ "where chnum = ?";
			List<ContentCategoryBean> ContentCategoryList = new ArrayList<>();

			try (Connection conn = ds.getConnection();
					PreparedStatement pst = conn.prepareStatement(sql);){
					pst.setInt(1, chnum);
					try(ResultSet rs = pst.executeQuery()) {
					
				while (rs.next()) {
					ContentCategoryBean ca = new ContentCategoryBean();
					ca.setChcate_Name(rs.getString(1));
					ContentCategoryList.add(ca);
				}

			} catch (NumberFormatException ex) {
				ex.printStackTrace();
			}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return ContentCategoryList;
		}

}
