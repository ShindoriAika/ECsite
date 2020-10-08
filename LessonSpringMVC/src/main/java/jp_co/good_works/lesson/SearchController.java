package jp_co.good_works.lesson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Scope("session")
public class SearchController {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
    ProductDao productDao;

	//-----a検索ボタン
		@RequestMapping(value = "/searchSearch", method = RequestMethod.POST)
		public String searchSearch(
				Model model,HttpSession session,
				@ModelAttribute ProductForm form,
				@RequestParam("product_name") String product_name,
				@RequestParam("category_id") int category_id) {

			List<ProductForm> productList = new ArrayList<ProductForm>();

			//-----"product_id""product_name""product_price"の取得
			List<Map<String, Object>> list = jdbcTemplate.queryForList(
					"SELECT product_id,product_name,product_price FROM ProductTbl WHERE product_name = ? OR category_id = ?",
					product_name,
					category_id);

			if (list.size() > 0) {
				for(int i = 0; i < list.size(); i++) {
					ProductForm p = new ProductForm();

						p.setProduct_id((Integer)list.get(i).get("product_id"));
						p.setProduct_name((String)list.get(i).get("product_name"));
						p.setProduct_price((Integer)list.get(i).get("product_price"));
					productList.add(p);
				}
				model.addAttribute("productList", productList);
				return "search";
			} else {
				model.addAttribute("errorMessage", "一致する商品がありません。");
				return "search";
			}
	}

	//-----a詳細ボタン
		@RequestMapping(value = "/searchDetail", method = RequestMethod.POST)
		public String searchDetail(
				Model model,HttpSession session,
				@ModelAttribute ProductForm form,
				 @RequestParam("Details") int Details) {

			ProductForm pf = new ProductForm();

			List<ProductForm> productList = new ArrayList<ProductForm>();

				pf = productDao.getDetails(Details);
				productList.add(pf);
				model.addAttribute("productList",productList);
				return "detail";
		}


	@Autowired
	ConfirmationDao confirmationDao;

	//-----aカートボタン
		@RequestMapping(value = "/cartShow", method = RequestMethod.POST)
		public String cartDispray(
			Model model,HttpSession session,
			@ModelAttribute CartForm form) {

			int acc_cd = (Integer)session.getAttribute("acc_cd");

			List<CartForm> cartList = new ArrayList<CartForm>();

			cartList = confirmationDao.getConfirmationDao(session);

			double zeinuki = 0;

			for(int i = 0; i < cartList.size(); i++) {
				//-----a商品一つの価格の計算
				int p = cartList.get(i).getProduct_price();
				int n = cartList.get(i).getNumber();
				int price1 = p*n;
				//-----a税抜きの合計価格
				zeinuki += price1;
			}

			//-----a税込みの合計価格
				int whole_amount = (int)(zeinuki * 1.1);
				int tax = whole_amount/110*10;

			//-----aカートの中身を確認
				List<Map<String, Object>> list = jdbcTemplate.queryForList(
						"SELECT * FROM orderDetailTbl WHERE acc_cd = ?",
						acc_cd);

				if (list.size() > 0) {
					model.addAttribute("cartList",cartList);
					model.addAttribute("whole_amount",whole_amount);
					model.addAttribute("tax",tax);
					return "cart";
				} else {
					model.addAttribute("errorMessage", "カートに商品がありません");
					return "search";
				}
		}
	}

