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
import dao.SalesDao;
import model.CartBean;
import model.CartProductBean;

@WebServlet("/ConfirmationServlet")
public class ConfirmationServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		CartBean CartBean = (CartBean)session.getAttribute("cart");

		for(CartProductBean CartProBean : CartBean.getCartProList()) {
			int stockNo = (CartProBean.getStockNo())-(CartProBean.getNumber());
			new ProductDao().updateStock(Integer.toString(CartProBean.getProCd()),Integer.toString(stockNo));

			new SalesDao().insertSales(CartBean.getUserId(),Integer.toString(CartProBean.getProCd()),CartProBean.getProPrice());
		}

		CartBean.setCartProList(new ArrayList<>());
		session.setAttribute("cart",CartBean);

		request.getRequestDispatcher("/view/Complete.jsp").forward(request,response);
	}
}
