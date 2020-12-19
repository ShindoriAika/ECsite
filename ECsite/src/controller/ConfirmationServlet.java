package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProductDao;
import dao.SalesDao;
import model.CartBean;
import model.CartProductBean;

@WebServlet("/ConfirmationServlet")
public class ConfirmationServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		CartBean cb = (CartBean)session.getAttribute("cart");

		for(CartProductBean cpb: cb.getCartProList()) {
			int stock_no = (cpb.getStock_no())-(cpb.getNumber());
			new ProductDao().updateStock(Integer.toString(cpb.getPro_cd()),Integer.toString(stock_no));

			new SalesDao().insertSales(cb.getUserId(),Integer.toString(cpb.getPro_cd()),cpb.getPro_price());
		}

		request.getRequestDispatcher("/view/Complete.jsp").forward(request,response);
	}

}
