package jp_co.good_works.lesson;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Scope("session")
public class FinishController {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	//-----a「はい」ボタン
		@RequestMapping(value = "/finishDelete", method = RequestMethod.POST)
		public String finish(HttpSession session){

			int acc_cd = (Integer)session.getAttribute("acc_cd");

		//-----a在庫を減らす
			List<Map<String,Object>> list = jdbcTemplate.queryForList(
				"SELECT * FROM orderDetailTbl WHERE acc_cd = ?",
				acc_cd);

				for(int i = 0; i < list.size(); i++) {
					int product_id = (Integer)list.get(i).get("product_id");

					List<Map<String,Object>> list1 = jdbcTemplate.queryForList(
							"SELECT * FROM orderDetailTbl WHERE acc_cd = ? and product_id = ?",
							acc_cd , product_id);

					List<Map<String,Object>> list2 = jdbcTemplate.queryForList(
							"SELECT * FROM StockTbl WHERE product_id = ?",
							product_id);

					int number = (Integer)list1.get(0).get("number");
					int stock = (Integer)list2.get(0).get("stock");

					int s = stock - number ;

					jdbcTemplate.update(
						"update StockTbl set stock = ? where product_id = ?",
						s , product_id);
				}

		//-----a注文明細テーブル(OrderDetailTbl)の削除
			jdbcTemplate.update("delete from OrderDetailTbl where acc_cd = ?;"
					,acc_cd);

			return "finish";
		}
}
