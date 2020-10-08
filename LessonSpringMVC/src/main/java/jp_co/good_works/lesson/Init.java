package jp_co.good_works.lesson;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Init {

	//a���O�C����ʕ\��
	@RequestMapping(value = "/")
	public String init1(Locale locale, Model model) {
		return "login";
	}

	//a�A�J�E���g�쐬��ʕ\��
	@RequestMapping(value = "/account")
	public String init2(Locale locale, Model model) {
		return "account";
	}

	//a������ʕ\��
		@RequestMapping(value = "/search")
		public String init3(Locale locale, Model model) {
			return "search";
		}

	//a������ʕ\��
		@RequestMapping(value = "/searchShow")
		public String init4(Locale locale, Model model) {
			return "searchShow";
		}

	//a�ڍ׉�ʕ\��
		@RequestMapping(value = "/detail")
		public String init5(Locale locale, Model model) {
			return "detail";
		}

	//a�J�[�g��ʕ\��
		@RequestMapping(value = "/cart")
		public String init6(Locale locale, Model model) {
			return "cart";
		}
		
	//a�J�[�g�ҏW��ʕ\��
		@RequestMapping(value = "/deleteCart")
		public String init7(Locale locale, Model model) {
			return "deleteCart";
		}

	//a�w���m�F��ʕ\��
		@RequestMapping(value = "/confirmation")
		public String init8(Locale locale, Model model) {
			return "confirmation";
		}

	//a�w��������ʕ\��
		@RequestMapping(value = "/finish")
		public String init9(Locale locale, Model model) {
			return "finish";
		}

	//a���O�A�E�g
		@RequestMapping(value = "/finishLogout", method = RequestMethod.POST)
		public String init10(Model model) {
			return "login";
		}

	//a�}�C�A�J�E���g��ʕ\��
		@RequestMapping(value = "/myaccount")
		public String init11(Locale locale, Model model) {
			return "myaccount";
		}

}
