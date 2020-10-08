package jp_co.good_works.lesson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ConfirmationDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<CartForm> getConfirmationDao(HttpSession session) {

		List<CartForm> cartList = new ArrayList<CartForm>();

		int acc_cd = (Integer)session.getAttribute("acc_cd");

	//-----a画面に表示させる情報を
	//-----a注文明細テーブル(OrderDetailTbl)から取得
		List<Map<String,Object>> list = jdbcTemplate.queryForList(
				"SELECT * FROM OrderDetailTbl where acc_cd = ?",
				acc_cd);

		for(int i = 0; i < list.size(); i++) {
			CartForm cf = new CartForm();
			cf.setOrder_id((Integer)list.get(i).get("order_id"));
			cf.setProduct_name((String)list.get(i).get("product_name"));
			cf.setProduct_price((Integer)list.get(i).get("price"));
			cf.setNumber((Integer)list.get(i).get("number"));

			cartList.add(cf);
		}

		return cartList;

	}
}


