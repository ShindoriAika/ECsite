package jp_co.good_works.lesson;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	//a���O�C������(���O�C���{�^���������ꂽ���̏���)
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(
			Model model,  HttpSession session,
			@RequestParam("acc_cd") int acc_cd,
			@RequestParam("login_pw") String login_pw) {

		//-----a�A�J�E���g���擾
		List<Map<String, Object>> list = jdbcTemplate.queryForList(
				"SELECT * FROM Account WHERE acc_cd = ? AND login_pw = ?",
				acc_cd,
				login_pw);

		if (list.size() > 0) {
			session.setAttribute("acc_cd",acc_cd);
			return "search";

		} else {
			model.addAttribute("errorMessage", "���O�C��ID�܂��̓p�X���[�h���Ԉ���Ă��܂��B");
			return "login";
		}

	}

}
