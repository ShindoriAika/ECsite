package model;

public class UserBean {

	int user_id;
	String login_cd;
	String login_pw;

	public UserBean() {

	}

	public UserBean(int user_id,String login_cd,String login_pw) {
		this.user_id = user_id;
		this.login_cd = login_cd;
		this.login_pw = login_pw;
	}

	public int getUser_id() {
		return user_id;
	}

	public String getLogin_cd() {
		return login_cd;
	}

	public String getLogin_pw() {
		return login_pw;
	}



}
