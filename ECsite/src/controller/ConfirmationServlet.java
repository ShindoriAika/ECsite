package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProductDao;
import model.CartBean;

@WebServlet("/ConfirmationServlet")
public class ConfirmationServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		ArrayList<CartBean> list = (ArrayList<CartBean>)session.getAttribute("cartList");

		for(CartBean cb:list) {
			int stock_no = (cb.getStock_no())-(cb.getNumber());
			new ProductDao().updateStock(Integer.toString(cb.getPro_cd()),Integer.toString(stock_no));
		}

		request.getRequestDispatcher("/view/Complete.jsp").forward(request,response);
	}

}
