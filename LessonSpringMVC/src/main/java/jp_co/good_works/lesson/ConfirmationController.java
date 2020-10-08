package jp_co.good_works.lesson;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Scope("session")
public class ConfirmationController {

	@Autowired
    ConfirmationDao confirmationDao;

	//-----a購入ボタン
		@RequestMapping(value = "/confirmationBuy", method = RequestMethod.POST)
		public String cartDispray(
			Model model,HttpSession session,
			@ModelAttribute CartForm form) {

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

				model.addAttribute("cartList",cartList);
				model.addAttribute("whole_amount",whole_amount);
				model.addAttribute("tax",tax);
				return "confirmation";
		}


	//-----aカート編集ボタン
		@RequestMapping(value = "/cartDeleteShow", method = RequestMethod.POST)
		public String cartDeleteShow(
			Model model,HttpSession session,
			@ModelAttribute CartForm form) {

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

				model.addAttribute("cartList",cartList);
				model.addAttribute("whole_amount",whole_amount);
				model.addAttribute("tax",tax);
				return "deleteCart";
		}
	}
