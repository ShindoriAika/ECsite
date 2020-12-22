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
import util.Price;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String flg = request.getParameter("flg");
		String proCdStr = request.getParameter("proCd");
		String numberStr = request.getParameter("number");

		HttpSession session = request.getSession(false);
		CartBean CartBean = (CartBean)session.getAttribute("cart");
		String proName = null;
		ArrayList<CartProductBean> CartProList = CartBean.getCartProList();

		switch(flg) {
			case "1":
				cartView(request,response);
				break;

			case "2":
				int proCd = Integer.parseInt(proCdStr);
				for(CartProductBean cpb : CartProList) {
					if(cpb.getProCd()==proCd) {
						proName = cpb.getProName();
						CartProList.remove(cpb);
						break;
					}
				}

				Price.price(CartBean);
				CartBean.setCartProList(CartProList);

				session.setAttribute("cart", CartBean);
				request.setAttribute("message",proName+"をカートから削除しました");

				request.getRequestDispatcher("/view/Cart.jsp").forward(request,response);
				break;

			case "3":
				int proCd = Integer.parseInt(proCdStr);
				int number = Integer.parseInt(numberStr);
				for(CartProductBean cpb : CartProList) {
					if(cpb.getProCd()==proCd) {
						cpb.setNumber(number);
						proName = cpb.getProName();
					}
				}

				Price.price(CartBean);
				CartBean.setCartProList(CartProList);

				session.setAttribute("cart", CartBean);
				request.setAttribute("message",proName+"の購入数を変更しました");

				request.getRequestDispatcher("/view/Cart.jsp").forward(request,response);
				break;

			case "4":

				break;
		}
	}

	private void cartView(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(((CartBean)session.getAttribute("cart")).getCartProList().size()==0) {
			request.setAttribute("errorMessage1","カートに商品が入っていません");
			request.getRequestDispatcher("/CategoryServlet").forward(request,response);
		}
		request.getRequestDispatcher("/view/Cart.jsp").forward(request,response);
	}

}
