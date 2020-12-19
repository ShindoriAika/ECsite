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

		int pro_cd = Integer.parseInt(request.getParameter("pro_cd"));
		String pro_name = request.getParameter("pro_name");
		int pro_price = Integer.parseInt(request.getParameter("pro_price"));
		int stock_no = Integer.parseInt(request.getParameter("stock_no"));
		int number = Integer.parseInt(request.getParameter("number"));

		HttpSession session = request.getSession(false);

		CartBean cb = (CartBean)session.getAttribute("cart");
		ArrayList<CartProductBean> list = cb.getCartProList();

		CartProductBean cpb = new CartProductBean(pro_cd,pro_name,pro_price,stock_no,number);
		list.add(cpb);

//		for(CartBean c :list) {
//			if(c == list.get(list.size()-1)){
//				break;
//			}else if(pro_cd == c.getPro_cd()) {
//				c.setNumber(c.getNumber()+number);
//				list.remove(list.size()-1);
//			}
//		}

		int total = 0;

		for(CartProductBean c :list) {
			total += (c.getPro_price())*(c.getNumber());
		}

		int totalAndTax = (int)(total * 1.1);
		int tax = totalAndTax - total;

		cb.setCartProList(list);
		cb.setTax(tax);
		cb.setTotal(totalAndTax);

		session.setAttribute("cart", cb);

		request.getRequestDispatcher("/view/Cart.jsp").forward(request,response);

	}

}
