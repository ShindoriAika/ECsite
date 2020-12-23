package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import model.ProductBean;

public class ProductDao extends Dao{

	ArrayList<ProductBean> ProductList = new ArrayList<>();

	public ArrayList<ProductBean> selectAll(){

		try {
			connection();
			stmt = conn.createStatement();
			String query = "select * from product";
			rs = stmt.executeQuery(query);

			while(rs.next()){
				ProductBean ProductBean = new ProductBean(rs.getInt("pro_cd"),rs.getString("pro_name"),
						rs.getInt("stock_no"),rs.getInt("pro_price"),rs.getInt("cat_id"),
						rs.getString("pro_img"),rs.getString("pro_msg"));

				ProductList.add(ProductBean);
			}

		} catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			close();
		}

		return ProductList;
	}

	public ArrayList<ProductBean> selectCatAndWord(int catName,String keyword){

		try {
			connection();
			String query = "select * from product where pro_name like ? and cat_id=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,"%"+keyword+"%");
			pstmt.setInt(2,catName);
			rs = pstmt.executeQuery();

			while(rs.next()){
				ProductBean ProductBean = new ProductBean(rs.getInt("pro_cd"),rs.getString("pro_name"),
						rs.getInt("stock_no"),rs.getInt("pro_price"),rs.getInt("cat_id"),
						rs.getString("pro_img"),rs.getString("pro_msg"));

				ProductList.add(ProductBean);
			}

		} catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			close();
		}

		return ProductList;
	}

	public ArrayList<ProductBean> selectCategory(int catName){

		try {
			connection();
			String query = "select * from product where cat_id=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,catName);
			rs = pstmt.executeQuery();

			while(rs.next()){
				ProductBean ProductBean = new ProductBean(rs.getInt("pro_cd"),rs.getString("pro_name"),
						rs.getInt("stock_no"),rs.getInt("pro_price"),rs.getInt("cat_id"),
						rs.getString("pro_img"),rs.getString("pro_msg"));

				ProductList.add(ProductBean);
			}

		} catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			close();
		}

		return ProductList;
	}

	public ArrayList<ProductBean> selectProName(String keyword){

		try {
			connection();
			String query = "select * from product where pro_name like ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,"%"+keyword+"%");
			rs = pstmt.executeQuery();

			while(rs.next()){
				ProductBean ProductBean = new ProductBean(rs.getInt("pro_cd"),rs.getString("pro_name"),
						rs.getInt("stock_no"),rs.getInt("pro_price"),rs.getInt("cat_id"),
						rs.getString("pro_img"),rs.getString("pro_msg"));

				ProductList.add(ProductBean);
			}

		} catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			close();
		}

		return ProductList;
	}

	public ProductBean selectProCode(String proCd){

		ProductBean ProductBean = null;

		try {
			connection();
			String query = "select p.pro_cd,p.pro_name,p.stock_no,p.pro_price,"
					+ "p.cat_id,p.pro_img,p.pro_msg,c.cat_name "
					+ "from product p,category c "
					+ "where p.pro_cd=? and p.cat_id=c.cat_id";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,proCd);
			rs = pstmt.executeQuery();

			rs.next();
			ProductBean = new ProductBean(rs.getInt("p.pro_cd"),rs.getString("p.pro_name"),
					rs.getInt("p.stock_no"),rs.getInt("p.pro_price"),rs.getInt("p.cat_id"),
					rs.getString("p.pro_img"),rs.getString("p.pro_msg"),rs.getString("c.cat_name"));

		} catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			close();
		}

		return ProductBean;
	}

	public void updateStock (String proCd,String stockNo){

		try {
			connection();
			String query = "update product set stock_no=? where pro_cd=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,stockNo);
			pstmt.setString(2,proCd);
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