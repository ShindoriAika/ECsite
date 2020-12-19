package model;

public class CartProductBean {

	int pro_cd;
	String pro_name;
	int pro_price;
	int stock_no;
	int number;

	public CartProductBean() {

	}

	public CartProductBean(int pro_cd,String pro_name,int pro_price,int stock_no,int number) {
		this.pro_cd = pro_cd;
		this.pro_name = pro_name;
		this.pro_price = pro_price;
		this.stock_no = stock_no;
		this.number = number;
	}

	public int getPro_cd() {
		return pro_cd;
	}

	public String getPro_name() {
		return pro_name;
	}

	public int getPro_price() {
		return pro_price;
	}

	public int getStock_no() {
		return stock_no;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}


}
