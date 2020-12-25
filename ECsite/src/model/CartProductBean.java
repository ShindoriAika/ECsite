package model;

public class CartProductBean {

	private int proCd;
	private String proName;
	private int proPrice;
	private int stockNo;
	private int number;

	public CartProductBean() {

	}

	public CartProductBean(int proCd,String proName,int proPrice,int stockNo,int number) {
		this.proCd = proCd;
		this.proName = proName;
		this.proPrice = proPrice;
		this.stockNo = stockNo;
		this.number = number;
	}

	public int getProCd() {
		return proCd;
	}

	public String getProName() {
		return proName;
	}

	public int getProPrice() {
		return proPrice;
	}

	public int getStockNo() {
		return stockNo;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}


}
