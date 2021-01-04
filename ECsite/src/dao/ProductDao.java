package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import model.ProductBean;

public class ProductDao extends Dao{

	ArrayList<ProductBean> ProductList = new ArrayList<>();

	public int countAll() {
		int count = 0;
		try {
			connection();
			stmt = conn.createStatement();
			String query = "select count(*) from product";
			rs = stmt.executeQuery(query);

			rs.next();
			count = rs.getInt(1);

		} catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			close();
		}

		return count;
	}

	public ArrayList<ProductBean> selectAll(int start){

		try {
			connection();
			String query = "select * from product limit ?,10";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,start);
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

	public int countCatAndWord(int catName,String keyword) {
		int count = 0;
		try {
			connection();
			String query = "select count(*) from product where pro_name like ? and cat_id=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,"%"+keyword+"%");
			pstmt.setInt(2,catName);
			rs = pstmt.executeQuery();

			rs.next();
			count = rs.getInt(1);

		} catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			close();
		}

		return count;
	}

	public ArrayList<ProductBean> selectCatAndWord(int catName,String keyword,int start){

		try {
			connection();
			String query = "select * from product where pro_name like ? and cat_id=? limit ?,10";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,"%"+keyword+"%");
			pstmt.setInt(2,catName);
			pstmt.setInt(3,start);
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

	public int countCategory(int catName) {
		int count = 0;
		try {
			connection();
			String query = "select count(*) from product where cat_id=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,catName);
			rs = pstmt.executeQuery();

			rs.next();
			count = rs.getInt(1);

		} catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			close();
		}

		return count;
	}

	public ArrayList<ProductBean> selectCategory(int catName,int start){

		try {
			connection();
			String query = "select * from product where cat_id=? limit ?,10";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,catName);
			pstmt.setInt(2,start);
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

	public int countProName(String keyword) {
		int count = 0;
		try {
			connection();
			String query = "select count(*) from product where pro_name like ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,"%"+keyword+"%");
			rs = pstmt.executeQuery();

			rs.next();
			count = rs.getInt(1);

		} catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			close();
		}

		return count;
	}

	public ArrayList<ProductBean> selectProName(String keyword,int start){

		try {
			connection();
			String query = "select * from product where pro_name like ? limit ?,10";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,"%"+keyword+"%");
			pstmt.setInt(2,start);
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

	public HashMap<Integer,Integer> selectStock(ArrayList<Integer> array) {
		HashMap<Integer,Integer> stockMap = new HashMap<>();

		try {
			connection();
			ArrayList<String> list = new ArrayList<>();
			for(int i:array) {
				list.add("?");
			}
			String str = String.join(",", list);
			StringBuilder sb = new StringBuilder("select pro_cd,stock_no from product where pro_cd in (");
			sb = sb.append(str).append(")");
			pstmt = conn.prepareStatement(sb.toString());
			for(int i=1;i<=array.size();i++) {
				pstmt.setInt(i,array.get(i-1));
			}
				rs = pstmt.executeQuery();

			while(rs.next()) {
				stockMap.put(rs.getInt("pro_cd"), rs.getInt("stock_no"));
			}

		} catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			close();
		}

		return stockMap;
	}

	public ProductBean selectProCode(int proCd){

		ProductBean ProductBean = null;

		try {
			connection();
			String query = "select p.pro_cd,p.pro_name,p.stock_no,p.pro_price,"
					+ "p.cat_id,p.pro_img,p.pro_msg,c.cat_name "
					+ "from product p,category c "
					+ "where p.pro_cd=? and p.cat_id=c.cat_id";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,proCd);
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

	public void updateStock (int proCd,int stockNo){

		try {
			connection();
			String query = "update product set stock_no=? where pro_cd=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,stockNo);
			pstmt.setInt(2,proCd);
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


