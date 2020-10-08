package jp_co.good_works.lesson;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {

		@Autowired
		private JdbcTemplate jdbcTemplate;

	//-----aアカウント作成
		@RequestMapping(value = "/accountCreate", method = RequestMethod.POST)
		public String accountCreate(
				Model model,
				@RequestParam("acc_cd") int acc_cd,
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

			//-----a日付取得
			Date ins_date = new Date();
			Date upd_date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd E HH:mm:ss");

			//-----aアカウントID
			List<Map<String, Object>> list = jdbcTemplate.queryForList(
					"SELECT * FROM Account WHERE acc_cd = ?",
					acc_cd);

			if (list.size() == 0) {
				//-----aアカウントテーブル(Account)に追加
				jdbcTemplate.update(
						"insert into Account (acc_cd , login_name , login_pw , gender , fullname , mail , tel_no , zip , prefecture , city , address , del_flg , ins_date , upd_date)"
						+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?); ",
						acc_cd , login_name , login_pw , gender , fullname , mail , tel_no , zip , prefecture , city , address , 0 , df.format(ins_date) , df.format(upd_date));
				return "login";

			} else {
				model.addAttribute("errorMessage", "入力したIDは使用できません。");
				return "account";
			}

		}
	}
