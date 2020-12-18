package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CartBean;

@WebServlet("/ProductDetailServlet")
public class ProductDetailServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int pro_cd = Integer.parseInt(request.getParameter("pro_cd"));
		String pro_name = request.getParameter("pro_name");
		int pro_price = Integer.parseInt(request.getParameter("pro_price"));
		int stock_no = Integer.parseInt(request.getParameter("stock_no"));
		int number = Integer.parseInt(request.getParameter("number"));

		HttpSession session = request.getSession(false);

		@SuppressWarnings("unchecked")
		ArrayList<CartBean> list = (ArrayList<CartBean>)session.getAttribute("cartList");

		if(list==null) {
			list = new ArrayList<CartBean>();
		}

		CartBean cb = new CartBean(pro_cd,pro_name,pro_price,stock_no,number);
		list.add(cb);

		for(CartBean c :list) {
			if(c == list.get(list.size()-1)){
				break;
			}else if(pro_cd == c.getPro_cd()) {
				c.setNumber(c.getNumber()+number);
				list.remove(list.size()-1);
			}
		}

		int total = 0;

		for(CartBean c :list) {
			total += (c.getPro_price())*(c.getNumber());
		}

		int totalAndTax = (int)(total * 1.1);
		int tax = totalAndTax - total;

		HashMap<String,Integer> map = new HashMap<>();
		map.put("tax",tax);
		map.put("total",totalAndTax);

		session.setAttribute("cartList",list);
		session.setAttribute("price", map);

		request.getRequestDispatcher("/view/Cart.jsp").forward(request,response);

	}

}
