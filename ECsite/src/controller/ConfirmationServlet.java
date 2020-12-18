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
import model.UserBean;

@WebServlet("/ConfirmationServlet")
public class ConfirmationServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		ArrayList<CartBean> cartList = (ArrayList<CartBean>)session.getAttribute("cartList");
		@SuppressWarnings("unchecked")
		ArrayList<UserBean> userList = (ArrayList<UserBean>)session.getAttribute("userList");

		for(CartBean cb:cartList) {
			int stock_no = (cb.getStock_no())-(cb.getNumber());
			new ProductDao().updateStock(Integer.toString(cb.getPro_cd()),Integer.toString(stock_no));

			for(UserBean ub:userList) {
				new SalesDao().insertSales(ub.getUser_id(),Integer.toString(cb.getPro_cd()),cb.getPro_price());
			}
		}

		request.getRequestDispatcher("/view/Complete.jsp").forward(request,response);
	}

}
