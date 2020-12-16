package model;

public class ProductBean {

	int pro_cd;
	String pro_name;
	int stock_no;
	int pro_price;
	int cat_id;
	String pro_img;
	String pro_msg;

	public ProductBean() {

	}

	public ProductBean(int pro_cd,String pro_name,int stock_no,int pro_price,
			int cat_id,String pro_img,String pro_msg){

		this.pro_cd = pro_cd;
		this.pro_name = pro_name;
		this.stock_no = stock_no;
		this.pro_price = pro_price;
		this.cat_id = cat_id;
		this.pro_img = pro_img;
		this.pro_msg = pro_msg;

	}

	public int getPro_cd() {
		return pro_cd;
	}

	public String getPro_name() {
		return pro_name;
	}

	public int getStock_no() {
		return stock_no;
	}

	public int getPro_price() {
		return pro_price;
	}

	public int getCat_id() {
		return cat_id;
	}

	public String getPro_img() {
		return pro_img;
	}

	public String getPro_msg() {
		return pro_msg;
	}




}
