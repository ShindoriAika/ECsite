package dao;

import java.sql.SQLException;

public class SalesDao extends Dao {

	public void insertSales (int userId,String proCd,int salesPrice){

		try {
			connection();
			String query = "insert into sales "
					+ "(user_id,pro_cd,salse_data,salse_price) "
					+ "values (?,?,now(),?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,userId);
			pstmt.setString(2,proCd);
			pstmt.setInt(3,salesPrice);
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
