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
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String login_cd = request.getParameter("login_cd");
		String login_pw = request.getParameter("login_pw");

		UserDao ud = new UserDao();
		ArrayList<UserBean> list = ud.selectLogin(login_cd,login_pw);

		if(login_cd=="" || login_pw=="") {

			request.setAttribute("errorMessage","名前とパスワードを入力してください");

			RequestDispatcher rd = request.getRequestDispatcher("/view/Login.jsp");
			rd.forward(request,response);

		} else if(list.size() == 0) {

			request.setAttribute("errorMessage","正しい名前とパスワードを入力してください");

			RequestDispatcher rd = request.getRequestDispatcher("/view/Login.jsp");
			rd.forward(request,response);

		} else {

			HttpSession session = request.getSession(true);
			session.setAttribute("login_cd",login_cd);
			session.setAttribute("login_pw",login_pw);

			RequestDispatcher rd = request.getRequestDispatcher("/CategoryServlet");
			rd.forward(request,response);

		}

	}

}
