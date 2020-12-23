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

    	String proCd = request.getParameter("proCd");

    	ProductBean ProductBean = new ProductDao().selectProCode(proCd);
    	request.setAttribute("product",ProductBean);

    	request.getRequestDispatcher("/view/ProductDetail.jsp").forward(request,response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String catName = request.getParameter("catName");
		String keyword = request.getParameter("keyword");

		ProductDao ProductDao = new ProductDao();
		ArrayList<ProductBean> ProductList = new ArrayList<>();

		if(catName=="" && keyword=="") {
			ProductList = ProductDao.selectAll();

		} else if(catName!="" && keyword!="") {
			ProductList = ProductDao.selectCatAndWord(Integer.parseInt(catName),keyword);

		} else if(catName!="") {
			ProductList = ProductDao.selectCategory(Integer.parseInt(catName));

		} else if(keyword!="") {
			ProductList = ProductDao.selectProName(keyword);
		}

		if(ProductList.size()==0) {
			request.setAttribute("errorMessage2","検索結果がありません");

		} else {
			request.setAttribute("product",ProductList);
		}

		request.getRequestDispatcher("/CategoryServlet").forward(request,response);
	}
}