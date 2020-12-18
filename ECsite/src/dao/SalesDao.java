package dao;

import java.sql.SQLException;

public class SalesDao extends Dao {

	public void insertSales (int user_id,String pro_cd,int sales_price){

		try {
			connection();
			String query = "insert into sales "
					+ "(user_id,pro_cd,salse_data,salse_price) "
					+ "values (?,?,now(),?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,user_id);
			pstmt.setString(2,pro_cd);
			pstmt.setInt(3,sales_price);
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
