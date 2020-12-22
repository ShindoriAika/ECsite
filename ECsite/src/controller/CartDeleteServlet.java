package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CartBean;
import model.CartProductBean;

@WebServlet("/CartDeleteServlet")
public class CartDeleteServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int proCd = Integer.parseInt(request.getParameter("proCd"));

		HttpSession session = request.getSession(false);
		CartBean CartBean = (CartBean)session.getAttribute("cart");
		String proName = null;

		ArrayList<CartProductBean> CartProList = CartBean.getCartProList();
		for(CartProductBean cpb : CartProList) {
			if(cpb.getProCd()==proCd) {
				proName = cpb.getProName();
				CartProList.remove(cpb);
				break;
			}
		}

		int total = 0;

		for(CartProductBean c : CartProList) {
			total += (c.getProPrice())*(c.getNumber());
		}

		int totalAndTax = (int)(total * 1.1);

		CartBean.setCartProList(CartProList);
		CartBean.setTotal(totalAndTax);
		CartBean.setTax(totalAndTax - total);

		session.setAttribute("cart", CartBean);
		request.setAttribute("message",proName+"をカートから削除しました");

		request.getRequestDispatcher("/view/Cart.jsp").forward(request,response);
	}
}
