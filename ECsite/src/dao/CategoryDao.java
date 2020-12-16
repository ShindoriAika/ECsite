package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import model.CategoryBean;

public class CategoryDao extends Dao{

	public ArrayList<CategoryBean> selectCategory(){

		ArrayList<CategoryBean> list = new ArrayList<>();

		try {
			connection();
			stmt = conn.createStatement();
			String query = "select * from category;";
			rs = stmt.executeQuery(query);

			while(rs.next()){
				CategoryBean cb = new CategoryBean(
						rs.getInt("cat_id"),rs.getString("cat_name"));

				list.add(cb);
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

}
