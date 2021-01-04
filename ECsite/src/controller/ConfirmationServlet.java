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

import dao.ProductDao;
import dao.SalesDao;
import model.CartBean;
import model.CartProductBean;
import util.Calculation;

@WebServlet("/ConfirmationServlet")
public class ConfirmationServlet extends HttpServlet {

	String proName = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String flg = request.getParameter("flg");

		switch(flg) {
		case "1":
			request.setAttribute("message","在庫がなくなったため、"+proName+"をカートから削除しました");
			request.getRequestDispatcher("/view/Cart.jsp").forward(request,response);
			break;
		case "2":
			request.getRequestDispatcher("/view/Complete.jsp").forward(request,response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		CartBean CartBean = (CartBean)session.getAttribute("cart");
		ArrayList<CartProductBean> CartProList = CartBean.getCartProList();
		ProductDao ProductDao = new ProductDao();

		ArrayList<Integer> proCdList = new ArrayList<>();

		for(CartProductBean CartProBean : CartProList) {
			proCdList.add(CartProBean.getProCd());
		}

		HashMap<Integer,Integer> stockMap = ProductDao.selectStock(proCdList);
		System.out.println(stockMap);

		for(CartProductBean CartProBean : CartProList) {
			int stockNo = stockMap.get(CartProBean.getProCd());
			if(stockNo!=0) {
				CartProBean.setStockNo(stockNo);
				stockNo = (CartProBean.getStockNo())-(CartProBean.getNumber());
				ProductDao.updateStock(CartProBean.getProCd(),stockNo);

				new SalesDao().insertSales(CartBean.getUserId(),CartProBean.getProCd(),CartProBean.getProPrice());
			}else {
				CartProList.remove(CartProBean);
				Calculation.priceCalculation(CartBean);
				CartBean.setCartProList(CartProList);
				session.setAttribute("cart", CartBean);
				proName = CartProBean.getProName();
				response.sendRedirect("/ECsite/ConfirmationServlet?flg=1");
			}
		}

		CartBean.setCartProList(new ArrayList<>());
		session.setAttribute("cart",CartBean);

		response.sendRedirect("/ECsite/ConfirmationServlet?flg=2");
	}
}
