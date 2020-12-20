package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CartBean;

@WebServlet("/CartViewServlet")
public class CartViewServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if(((CartBean)session.getAttribute("cart")).getCartProList().size()==0) {
			request.setAttribute("errorMessage1","カートに商品が入っていません");
			request.getRequestDispatcher("/CategoryServlet").forward(request,response);
		}

		request.getRequestDispatcher("/view/Cart.jsp").forward(request,response);
	}
}
