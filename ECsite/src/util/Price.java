package util;

import model.CartBean;
import model.CartProductBean;

public class Price {

	public static void price(CartBean CartBean) {
		int total = 0;

		for(CartProductBean c : CartBean.getCartProList()) {
			total += (c.getProPrice())*(c.getNumber());
		}

		int totalAndTax = (int)(total * 1.1);

		CartBean.setTotal(totalAndTax);
		CartBean.setTax(totalAndTax - total);
	}

}
