package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;

@WebServlet("/MakeAccountServlet")
public class MakeAccountServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("errorMessage","登録できました");
		request.getRequestDispatcher("/view/Login.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String loginCd = request.getParameter("loginCd");
		String loginPw = request.getParameter("loginPw");

		if(loginCd.length()>50||loginPw.length()>50) {
			request.setAttribute("message","50文字以内で入力してください");
			request.getRequestDispatcher("/view/MakeAccount.jsp").forward(request,response);

		}else if(loginCd==""||loginPw=="") {
			request.setAttribute("message","入力してください");
			request.getRequestDispatcher("/view/MakeAccount.jsp").forward(request,response);

		}else {
			new UserDao().insertAccount(loginCd,loginPw);
			response.sendRedirect("/ECsite/MakeAccountServlet");
		}
	}
}
