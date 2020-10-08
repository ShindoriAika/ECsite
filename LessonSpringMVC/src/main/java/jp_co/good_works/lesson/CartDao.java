package jp_co.good_works.lesson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CartDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<CartForm> getCartDao(int product_id , int number ,int acc_cd) {

		List<CartForm> cartList = new ArrayList<CartForm>();

	//-----aログインIDと商品名と単価を取得
		List<Map<String,Object>> list = jdbcTemplate.queryForList(
				"SELECT * FROM ProductTbl WHERE product_id = ?",
				product_id);

		String product_name = (String)list.get(0).get("product_name");
		int product_price = (Integer)list.get(0).get("product_price");

	//-----a日付
		Date ins_date = new Date();
		Date upd_date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd E HH:mm:ss");

	//-----a同じ商品
		List<Map<String,Object>> list0 = jdbcTemplate.queryForList(
				"SELECT * FROM orderDetailTbl WHERE product_id = ? and acc_cd = ?",
				product_id , acc_cd);

		if (list0.size() > 0) {
			int n1 = (Integer)list0.get(0).get("number");
			int n2 = n1 + number;

			jdbcTemplate.update(
					"update OrderDetailTbl set number = ? where acc_cd = ? and product_id = ?",
					n2 , acc_cd , product_id);

		} else {
		//-----a注文明細テーブル(OrderDetailTbl)に登録
			jdbcTemplate.update(
					"insert into OrderDetailTbl (acc_cd , product_id , product_name , number , price , del_flg , ins_date , upd_date)"
					+ "values (?,?,?,?,?,?,?,?); ",
					acc_cd , product_id , product_name , number , product_price , 0 , df.format(ins_date) , df.format(upd_date));
		}

	//-----a画面に表示させる情報を
	//-----a注文明細テーブル(OrderDetailTbl)から取得
		List<Map<String,Object>> list2 = jdbcTemplate.queryForList(
				"SELECT * FROM OrderDetailTbl where acc_cd = ?",
				acc_cd);

		for(int i = 0; i < list2.size(); i++) {

			CartForm cf = new CartForm();
			cf.setOrder_id((Integer)list2.get(i).get("order_id"));
			cf.setProduct_name((String)list2.get(i).get("product_name"));
			cf.setProduct_price((Integer)list2.get(i).get("price"));
			cf.setNumber((Integer)list2.get(i).get("number"));

			cartList.add(cf);
		}

		return cartList;

	}
}