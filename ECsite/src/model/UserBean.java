package model;

public class UserBean {

	private int userId;
	private String loginCd;
	private String loginPw;

	public UserBean() {
	}

	public UserBean(int userId,String loginCd,String loginPw) {
		this.userId = userId;
		this.loginCd = loginCd;
		this.loginPw = loginPw;
	}

	public int getUserId() {
		return userId;
	}
}
