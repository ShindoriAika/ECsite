package model;

public class CategoryBean {
	int cat_id;
	String cat_name;

	public CategoryBean() {

	}

	public CategoryBean(int cat_id,String cat_name) {
		this.cat_id = cat_id;
		this.cat_name = cat_name;
	}

	public int getCat_id() {
		return cat_id;
	}

	public String getCat_name() {
		return cat_name;
	}


}
