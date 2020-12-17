package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CartBean;

/**
 * Servlet implementation class ProductDetailServlet
 */
@WebServlet("/ProductDetailServlet")
public class ProductDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		int pro_cd = Integer.parseInt(request.getParameter("pro_cd"));
		String pro_name = request.getParameter("pro_name");
		int pro_price = Integer.parseInt(request.getParameter("pro_price"));
		int stock_no = Integer.parseInt(request.getParameter("stock_no"));
		int number = Integer.parseInt(request.getParameter("number"));

		HttpSession session = request.getSession(false);

		@SuppressWarnings("unchecked")
		ArrayList<CartBean> list = (ArrayList<CartBean>)session.getAttribute("cartList");

		if(list==null) {
			list = new ArrayList<>();
		}

		CartBean cb = new CartBean(pro_cd,pro_name,pro_price,stock_no,number);
		list.add(cb);
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

		RequestDispatcher rd = request.getRequestDispatcher("/view/Cart.jsp");
		rd.forward(request,response);

	}

}
