package util;

import model.CartBean;
import model.CartProductBean;

public class Calculation {

	static final double TAX = 1.1;

	public static void priceCalculation(CartBean CartBean) {
		int totalPrice = 0;

		for(CartProductBean c : CartBean.getCartProList()) {
			totalPrice += (c.getProPrice())*(c.getNumber());
		}

		int totalTaxIncludedPrice = (int)(totalPrice * TAX);

		CartBean.setTotal(totalTaxIncludedPrice);
		CartBean.setTax(totalTaxIncludedPrice - totalPrice);
	}

}