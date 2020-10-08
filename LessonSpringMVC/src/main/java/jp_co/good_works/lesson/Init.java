package jp_co.good_works.lesson;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Init {

	//aログイン画面表示
	@RequestMapping(value = "/")
	public String init1(Locale locale, Model model) {
		return "login";
	}

	//aアカウント作成画面表示
	@RequestMapping(value = "/account")
	public String init2(Locale locale, Model model) {
		return "account";
	}

	//a検索画面表示
		@RequestMapping(value = "/search")
		public String init3(Locale locale, Model model) {
			return "search";
		}

	//a検索画面表示
		@RequestMapping(value = "/searchShow")
		public String init4(Locale locale, Model model) {
			return "searchShow";
		}

	//a詳細画面表示
		@RequestMapping(value = "/detail")
		public String init5(Locale locale, Model model) {
			return "detail";
		}

	//aカート画面表示
		@RequestMapping(value = "/cart")
		public String init6(Locale locale, Model model) {
			return "cart";
		}
		
	//aカート編集画面表示
		@RequestMapping(value = "/deleteCart")
		public String init7(Locale locale, Model model) {
			return "deleteCart";
		}

	//a購入確認画面表示
		@RequestMapping(value = "/confirmation")
		public String init8(Locale locale, Model model) {
			return "confirmation";
		}

	//a購入完了画面表示
		@RequestMapping(value = "/finish")
		public String init9(Locale locale, Model model) {
			return "finish";
		}

	//aログアウト
		@RequestMapping(value = "/finishLogout", method = RequestMethod.POST)
		public String init10(Model model) {
			return "login";
		}

	//aマイアカウント画面表示
		@RequestMapping(value = "/myaccount")
		public String init11(Locale locale, Model model) {
			return "myaccount";
		}

}
