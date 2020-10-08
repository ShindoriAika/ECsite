package jp_co.good_works.lesson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public ProductForm getDetails(int product_id) {
		ProductForm pf = new ProductForm();

		//-----"product_name""product_price""product_detail"
		//-----"product_img""category_id"�̎擾
			List<Map<String,Object>> list = jdbcTemplate.queryForList(
				"SELECT * FROM ProductTbl WHERE product_id = ?",
				product_id);

		//-----"category_name"�̎擾
			int category_id = (Integer)list.get(0).get("category_id");
			List<Map<String,Object>> list2 = jdbcTemplate.queryForList(
				"SELECT category_name FROM CategoryTbl WHERE category_id = ?",
				category_id);

		//-----"stock"�̎擾
			List<Map<String,Object>> list3 = jdbcTemplate.queryForList(
				"SELECT stock FROM StockTbl WHERE product_id = ?",
				product_id);

		pf.setProduct_id(product_id);
		pf.setProduct_img((String)list.get(0).get("product_img"));
		pf.setProduct_name((String)list.get(0).get("product_name"));
		pf.setProduct_price((Integer)list.get(0).get("product_price"));
		pf.setProduct_detail((String)list.get(0).get("product_detail"));
		pf.setCategory_name((String)list2.get(0).get("category_name"));
		pf.setStock((Integer)list3.get(0).get("Stock"));

		return pf;
	}
}

