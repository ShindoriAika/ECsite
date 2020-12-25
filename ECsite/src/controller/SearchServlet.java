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

	static final int SEARCH_MAX_NUMBER = 10;

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
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		int start = pageNumber*SEARCH_MAX_NUMBER - SEARCH_MAX_NUMBER;
		System.out.println(start);

		ProductDao ProductDao = new ProductDao();
		ArrayList<ProductBean> ProductList = new ArrayList<>();
		double count = 0.0;

		if(catName=="" && keyword=="") {
			count = ProductDao.countAll();
			ProductList = ProductDao.selectAll(start);

		} else if(catName!="" && keyword!="") {
			count = ProductDao.countCatAndWord(Integer.parseInt(catName),keyword);
			ProductList = ProductDao.selectCatAndWord(Integer.parseInt(catName),keyword,start);

		} else if(catName!="") {
			count = ProductDao.countCategory(Integer.parseInt(catName));
			ProductList = ProductDao.selectCategory(Integer.parseInt(catName),start);

		} else if(keyword!="") {
			count = ProductDao.countProName(keyword);
			ProductList = ProductDao.selectProName(keyword,start);
		}

		if(ProductList.size()==0) {
			request.setAttribute("errorMessage2","検索結果がありません");

		} else {
			count/=SEARCH_MAX_NUMBER;
			request.setAttribute("count", Math.ceil(count));
			request.setAttribute("product",ProductList);
			request.setAttribute("catName",catName);
			request.setAttribute("keyword",keyword);
			System.out.println(count);
		}

		request.getRequestDispatcher("/CategoryServlet").forward(request,response);
	}
}
