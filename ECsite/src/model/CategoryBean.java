package model;

public class CategoryBean {
	private int catId;
	private String catName;

	public CategoryBean() {
	}

	public CategoryBean(int catId,String catName) {
		this.catId = catId;
		this.catName = catName;
	}

	public int getCatId() {
		return catId;
	}

	public String getCatName() {
		return catName;
	}
}
