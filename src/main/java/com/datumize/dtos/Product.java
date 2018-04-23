package com.datumize.dtos;

public class Product {

	private int id;
	private String name;
	private String descr;
	private int price;
	private Category category;

	public Product(int anID, String aName, String aDescr, int aPrice, Category aCategory) {
		this.id = anID;
		this.name = aName;
		this.descr = aDescr;
		this.price = aPrice;
		this.category = aCategory;
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getDescr() {
		return this.descr;
	}


	public int getPrice() {
		return this.price;
	}

	public Category getCategory() {
		return this.category;
	}

}
