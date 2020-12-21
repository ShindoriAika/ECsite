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

@WebServlet("/ProductDetailServlet")
public class ProductDetailServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int proCd = Integer.parseInt(request.getParameter("proCd"));
		String proName = request.getParameter("proName");
		int proPrice = Integer.parseInt(request.getParameter("proPrice"));
		int stockNo = Integer.parseInt(request.getParameter("stockNo"));
		int number = Integer.parseInt(request.getParameter("number"));

		HttpSession session = request.getSession(false);

		CartBean CartBean = (CartBean)session.getAttribute("cart");
		ArrayList<CartProductBean> CartProList = CartBean.getCartProList();

		CartProductBean CartProBean = new CartProductBean(proCd,proName,proPrice,stockNo,number);
		CartProList.add(CartProBean);
		boolean flg = false;

		for(CartProductBean cpb : CartProList) {
			if(cpb == CartProList.get(CartProList.size()-1)){
				break;
			}else if(proCd == cpb.getProCd()) {
				cpb.setNumber(cpb.getNumber()+number);
				flg = true;
			}
		}
		if(flg) {
			CartProList.remove(CartProList.size()-1);
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

		request.getRequestDispatcher("/view/Cart.jsp").forward(request,response);

	}
}
