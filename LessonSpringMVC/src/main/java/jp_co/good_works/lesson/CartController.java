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


	//-----a�u�J�[�g�ցv�{�^��
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
				//-----a���i��̉��i�̌v�Z
				int p = cartList.get(i).getProduct_price();
				int n = cartList.get(i).getNumber();
				int price1 = p*n;
				//-----a�Ŕ����̍��v���i
				zeinuki += price1;
			}

			//-----a�ō��݂̍��v���i
			int whole_amount = (int)(zeinuki * 1.1);
			int tax = whole_amount/110*10;

				model.addAttribute("cartList",cartList);
				model.addAttribute("whole_amount",whole_amount);
				model.addAttribute("tax",tax);
				return "cart";

		}

	//-----a�폜�{�^��
		@RequestMapping(value = "/cartDelete", method = RequestMethod.POST)
		public String cartDelete(
			Model model,HttpSession session,
			@ModelAttribute CartForm form,
			@RequestParam("cartDelete") int cartDelete) {

			//-----a�J�[�g�Ȃ��̕����폜
			jdbcTemplate.update("delete from OrderDetailTbl where order_id = ?;"
					,cartDelete);

			model.addAttribute("errorMessage", "�J�[�g�̓��e��ύX���܂���");
			return "search";
		}

}
