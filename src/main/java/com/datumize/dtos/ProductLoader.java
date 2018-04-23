package com.datumize.dtos;

import java.util.ArrayList;
import java.util.List;

public class ProductLoader {
	
	

	public List<Product> loadStock(){
		
		List<Product> productList = new ArrayList<Product>();
		
		Department groceryDept = new Department(1, "Grocery", "Grocery like fruits and veggies categories");
		Department beautyDept = new Department(2, "Beauty", "Beauty like Soaps and Deodorant categories");
		
		Category fruitCategory = new Category(1, "Fruit", "Fruits like apple,guava products", groceryDept);
		Category vegetableCategory = new Category(2, "Vegetables", "Vegetables like onion,tomato products", groceryDept);
		Category soapCategory = new Category(3, "Soap", "Soap like Shampoo,Soap,Facewash products ", beautyDept);
		Category deoCategory =new Category(4, "Deo", "Deo like Deospray,Deodrant,Perfume products ", beautyDept);

		Product shampoo = new Product(100, "Shampoo", "shampoo",  100, soapCategory);
		Product facewash= new Product(101, "Facewash", "facewash",  50, soapCategory);
		Product deo= new Product(102, "Deo", "deo",  150, deoCategory);
		Product perfume= new Product(103, "Perfume", "perfume",  400, deoCategory);
		
		Product apple= new Product(104, "Apple", "apple",  10, fruitCategory);
		Product guava= new Product(105, "Guava", "guava",  5, fruitCategory);
		Product onion= new Product(106, "Onion", "onion",  50, vegetableCategory);
		Product tomato= new Product(107, "Tomato", "tomato",  70, vegetableCategory);
		
		
		productList.add(shampoo);
		productList.add(facewash);
		productList.add(deo);
		productList.add(perfume);
		productList.add(apple);
		productList.add(guava);
		productList.add(onion);
		productList.add(tomato);
		
		
		return productList;
		
	}
	



}
