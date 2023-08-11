package ChannelCategory.DB;

public class ChannelCategoryBean {
	private int categoryId;
	private String categoryName;


	public int getCategoryId(int cateId) {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName(String cateName) {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
