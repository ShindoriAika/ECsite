package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import model.UserBean;

public class UserDao extends Dao{

	public ArrayList<UserBean> selectLogin(String login_cd,String login_pw){

		ArrayList<UserBean> list = new ArrayList<>();

		try {
			connection();
			String query = "select * from user where login_cd= ? and login_pw= ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,login_cd);
			pstmt.setString(2,login_pw);
			rs = pstmt.executeQuery();

			while(rs.next()){
				UserBean ub = new UserBean(
						rs.getInt("user_id"),rs.getString("login_cd"),rs.getString("login_pw"));

				list.add(ub);
			}

		} catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			close();
		}

		return list;
	}

	public void insertAccount (String login_cd,String login_pw){

		try {
			connection();
			String query = "insert into user (login_cd,login_pw) values (?,?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,login_cd);
			pstmt.setString(2,login_pw);
			pstmt.executeUpdate();

		} catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			close();
		}
	}

}
