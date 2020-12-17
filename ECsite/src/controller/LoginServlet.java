package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import model.UserBean;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String loginCd = request.getParameter("loginCd");
		String loginPw = request.getParameter("loginPw");

		if(loginCd=="" || loginPw=="") {
			request.setAttribute("errorMessage","名前とパスワードを入力してください");

			RequestDispatcher rd = request.getRequestDispatcher("/view/Login.jsp");
			rd.forward(request,response);

			return;
		}

		UserDao userDao = new UserDao();
		ArrayList<UserBean> userList = userDao.selectLogin(loginCd,loginPw);

		if(userList.size() == 0) {
			request.setAttribute("errorMessage","正しい名前とパスワードを入力してください");

			RequestDispatcher rd = request.getRequestDispatcher("/view/Login.jsp");
			rd.forward(request,response);

		} else {
			HttpSession session = request.getSession(true);
			session.setAttribute("loginCd",loginCd);

			RequestDispatcher rd = request.getRequestDispatcher("/CategoryServlet");
			rd.forward(request,response);
		}

	}

}
