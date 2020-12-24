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
import util.Calculation;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String flg = request.getParameter("flg");

		switch(flg) {
			case "1":
				cartView(request,response);
				break;

			case "2":
				cartDelete(request,response);
				break;

			case "3":
				cartChange(request,response);
				break;

			case "4":
				cartNo(request,response);
				break;
		}
	}

	private void cartView(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(((CartBean)session.getAttribute("cart")).getCartProList().size()==0) {
			request.setAttribute("errorMessage1","カートに商品が入っていません");
			request.getRequestDispatcher("/CategoryServlet").forward(request,response);
		}else {
			request.getRequestDispatcher("/view/Cart.jsp").forward(request,response);
		}
	}

	private void cartDelete(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		int proCd = Integer.parseInt(request.getParameter("proCd"));

		HttpSession session = request.getSession(false);
		CartBean CartBean = (CartBean)session.getAttribute("cart");

		ArrayList<CartProductBean> CartProList = CartBean.getCartProList();
		String proName = null;

		for(CartProductBean cpb : CartProList) {
			if(cpb.getProCd()==proCd) {
				proName = cpb.getProName();
				CartProList.remove(cpb);
				break;
			}
		}

		Calculation.priceCalculation(CartBean);
		CartBean.setCartProList(CartProList);

		session.setAttribute("cart", CartBean);
		request.setAttribute("message",proName+"をカートから削除しました");

		request.getRequestDispatcher("/view/Cart.jsp").forward(request,response);
	}

	private void cartChange(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		int proCd = Integer.parseInt(request.getParameter("proCd"));
		int number = Integer.parseInt(request.getParameter("number"));

		HttpSession session = request.getSession(false);
		CartBean CartBean = (CartBean)session.getAttribute("cart");

		ArrayList<CartProductBean> CartProList = CartBean.getCartProList();
		String proName = null;

		for(CartProductBean cpb : CartProList) {
			if(cpb.getProCd()==proCd) {
				cpb.setNumber(number);
				proName = cpb.getProName();
			}
		}

		Calculation.priceCalculation(CartBean);
		CartBean.setCartProList(CartProList);

		session.setAttribute("cart", CartBean);
		request.setAttribute("message",proName+"の購入数を変更しました");

		request.getRequestDispatcher("/view/Cart.jsp").forward(request,response);
	}

	private void cartNo(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession(false);
		if(((CartBean)session.getAttribute("cart")).getCartProList().size()==0) {
			request.setAttribute("message","カートに商品が入っていません");
			request.getRequestDispatcher("/view/Cart.jsp").forward(request,response);
		}else {
			request.getRequestDispatcher("/view/Confirmation.jsp").forward(request,response);
		}
	}

}