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
import model.CartProductBean;
import util.Calculation;

@WebServlet("/ProductDetailServlet")
public class ProductDetailServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.getRequestDispatcher("/view/Cart.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int proCd = Integer.parseInt(request.getParameter("proCd"));
		String proName = request.getParameter("proName");
		int proPrice = Integer.parseInt(request.getParameter("proPrice"));
		int stockNo = Integer.parseInt(request.getParameter("stockNo"));
		int number = Integer.parseInt(request.getParameter("number"));

		HttpSession session = request.getSession(false);

		CartBean CartBean = (CartBean)session.getAttribute("cart");
		ArrayList<CartProductBean> CartProList = CartBean.getCartProList();

		CartProList.add(new CartProductBean(proCd,proName,proPrice,stockNo,number));

		HashMap<Integer,ArrayList<CartProductBean>> productMap = new HashMap<>();
		for(CartProductBean CartProBean:CartProList) {
			if(productMap.containsKey(CartProBean.getProCd())) {
				ArrayList<CartProductBean> list = productMap.get(CartProBean.getProCd());
				list.add(CartProBean);
				productMap.put(CartProBean.getProCd(),list);
			}else {
				ArrayList<CartProductBean> list = new ArrayList<>();
				list.add(CartProBean);
				productMap.put(CartProBean.getProCd(),list);
			}
		}

		CartProList = new ArrayList<>();
		for(ArrayList<CartProductBean> cbl:productMap.values()) {
			int proNumber = 0;
			for(CartProductBean cpb:cbl) {
				cbl.get(0).setNumber(proNumber+=cpb.getNumber());
			}
			CartProList.add(cbl.get(0));
		}

		Calculation.priceCalculation(CartBean);
		CartBean.setCartProList(CartProList);

		session.setAttribute("cart", CartBean);

		response.sendRedirect("/ECsite/ProductDetailServlet");
	}
}
