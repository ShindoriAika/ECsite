package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;

/**
 * Servlet implementation class MakeAccountServlet
 */
@WebServlet("/MakeAccountServlet")
public class MakeAccountServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String loginCd = request.getParameter("loginCd");
		String loginPw = request.getParameter("loginPw");

		new UserDao().insertAccount(loginCd,loginPw);

		request.setAttribute("message","登録できました");
		request.getRequestDispatcher("/view/MakeAccount.jsp").forward(request,response);
	}
}
