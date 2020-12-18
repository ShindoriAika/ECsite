package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDao;
import model.ProductBean;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	String pro_cd = request.getParameter("pro_cd");

    	ProductBean pb = new ProductDao().selectProCode(pro_cd);
    	request.setAttribute("product",pb);

    	request.getRequestDispatcher("/view/ProductDetail.jsp").forward(request,response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String cat_name = request.getParameter("cat_name");
		String keyword = request.getParameter("keyword");

		ProductDao pd = new ProductDao();
		ArrayList<ProductBean> list = new ArrayList<>();

		if(cat_name=="" && keyword=="") {
			list = pd.selectAll();

		} else if(cat_name!="" && keyword!="") {
			list = pd.selectCatAndWord(Integer.parseInt(cat_name),keyword);

		} else if(cat_name!="") {
			list = pd.selectCategory(Integer.parseInt(cat_name));

		} else if(keyword!="") {
			list = pd.selectProName(keyword);
		}

		if(list.size()==0) {
			request.setAttribute("errorMessage","検索結果がありません");

		} else {
			request.setAttribute("product",list);
		}

		request.getRequestDispatcher("/CategoryServlet").forward(request,response);
	}
}
