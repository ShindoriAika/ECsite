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
public class CartController {

	@Autowired
    CartDao cartDao;

	@Autowired
	private JdbcTemplate jdbcTemplate;


	//-----a「カートへ」ボタン
		@RequestMapping(value = "/cartDisplay", method = RequestMethod.POST)
		public String cartDispray(
			Model model,HttpSession session,
			@ModelAttribute CartForm form,
			@RequestParam("number") int number,
			@RequestParam("CproductId") int CproductId) {

			List<CartForm> cartList = new ArrayList<CartForm>();

			int acc_cd = (Integer)session.getAttribute("acc_cd");

			cartList = cartDao.getCartDao(CproductId,number,acc_cd);

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

				model.addAttribute("cartList",cartList);
				model.addAttribute("whole_amount",whole_amount);
				model.addAttribute("tax",tax);
				return "cart";

		}

	//-----a削除ボタン
		@RequestMapping(value = "/cartDelete", method = RequestMethod.POST)
		public String cartDelete(
			Model model,HttpSession session,
			@ModelAttribute CartForm form,
			@RequestParam("cartDelete") int cartDelete) {

			//-----aカートないの物を削除
			jdbcTemplate.update("delete from OrderDetailTbl where order_id = ?;"
					,cartDelete);

			model.addAttribute("errorMessage", "カートの内容を変更しました");
			return "search";
		}

}
