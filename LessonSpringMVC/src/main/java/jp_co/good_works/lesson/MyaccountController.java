package jp_co.good_works.lesson;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyaccountController {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	//-----a�A�J�E���g�X�V
		@RequestMapping(value = "/myaccountUpdate", method = RequestMethod.POST)
		public String myaccountUpdate(
				Model model, HttpSession session,
				@RequestParam("login_name") String login_name,
				@RequestParam("login_pw") String login_pw,
				@RequestParam("gender") String gender,
				@RequestParam("fullname") String fullname,
				@RequestParam("mail") String mail,
				@RequestParam("tel_no") String tel_no,
				@RequestParam("zip") String zip,
				@RequestParam("prefecture") String prefecture,
				@RequestParam("city") String city,
				@RequestParam("address") String address) {

			int acc_cd = (Integer)session.getAttribute("acc_cd");

			//-----a���t
			Date upd_date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd E HH:mm:ss");

			//-----a�A�J�E���g�e�[�u���̍X�V
			jdbcTemplate.update(
				"update Account set login_name = ?, login_pw = ?, gender = ?, fullname = ?, mail = ?, tel_no = ?, zip = ?, prefecture = ?, city = ?, address = ?, upd_date = ? where acc_cd = ? ; ",
				login_name , login_pw , gender , fullname , mail , tel_no , zip , prefecture , city , address , df.format(upd_date) , acc_cd);

			model.addAttribute("errorMessage", "�A�J�E���g�����X�V���܂����B");
			return "search";
		}


	//-----a�A�J�E���g�폜
		@RequestMapping(value = "/myaccountDelete", method = RequestMethod.POST)
		public String myaccountDelete(
				Model model, HttpSession session) {

			int acc_cd = (Integer)session.getAttribute("acc_cd");

			//-----del_flg��"1"�ɂ���
			jdbcTemplate.update(
				"update Account set del_flg = ? where acc_cd = ? ; ",
				1 , acc_cd);

			model.addAttribute("errorMessage", "�A�J�E���g�����폜���܂����B");
			return "login";
		}
}
