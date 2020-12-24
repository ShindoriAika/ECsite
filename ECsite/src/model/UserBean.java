package model;

public class UserBean {

	int userId;
	String loginCd;
	String loginPw;

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
