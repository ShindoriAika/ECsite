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
import model.CartProductBean;
import util.Calculation;

@WebServlet("/ConfirmationServlet")
public class ConfirmationServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		CartBean CartBean = (CartBean)session.getAttribute("cart");
		ArrayList<CartProductBean> CartProList = CartBean.getCartProList();
		ProductDao ProductDao = new ProductDao();

		for(CartProductBean CartProBean : CartProList) {
			if(ProductDao.selectStock(CartProBean.getProCd())!=0) {
				int stockNo = (CartProBean.getStockNo())-(CartProBean.getNumber());
				ProductDao.updateStock(Integer.toString(CartProBean.getProCd()),Integer.toString(stockNo));

				new SalesDao().insertSales(CartBean.getUserId(),CartProBean.getProCd(),CartProBean.getProPrice());
			}else {
				CartProList.remove(CartProBean);
				Calculation.priceCalculation(CartBean);
				CartBean.setCartProList(CartProList);
				session.setAttribute("cart", CartBean);
				request.setAttribute("message","在庫がなくなったため、"+CartProBean.getProName()+"をカートから削除しました");
				request.getRequestDispatcher("/view/Cart.jsp").forward(request,response);
			}
		}

		CartBean.setCartProList(new ArrayList<>());
		session.setAttribute("cart",CartBean);

		request.getRequestDispatcher("/view/Complete.jsp").forward(request,response);
	}
}
