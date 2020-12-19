package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import model.CartBean;
import model.CartProductBean;
import model.UserBean;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String loginCd = request.getParameter("loginCd");
		String loginPw = request.getParameter("loginPw");

		if(loginCd=="" || loginPw=="") {
			request.setAttribute("errorMessage","名前とパスワードを入力してください");
			request.getRequestDispatcher("/view/Login.jsp").forward(request,response);
			return;
		}

		UserBean ub = new UserDao().selectLogin(loginCd,loginPw);

		if(ub.getUserId() == 0) {
			request.setAttribute("errorMessage","正しい名前とパスワードを入力してください");
			request.getRequestDispatcher("/view/Login.jsp").forward(request,response);

		} else {
			CartBean cb = new CartBean();
			cb.setUserId(ub.getUserId());
			cb.setCartProList(new ArrayList<CartProductBean>());

			HttpSession session = request.getSession(true);
			session.setAttribute("cart",cb);
			request.getRequestDispatcher("/CategoryServlet").forward(request,response);
		}
	}
}
