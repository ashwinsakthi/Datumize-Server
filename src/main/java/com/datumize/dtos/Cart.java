package com.datumize.dtos;

import java.util.ArrayList;
import java.util.List;

public class Cart {

	static List<Product> cartList = new ArrayList<Product>();

	void add(Product product) {
		cartList.add(product);
	}

	void remove(Product product) {
		cartList.remove(product);
	}
}
