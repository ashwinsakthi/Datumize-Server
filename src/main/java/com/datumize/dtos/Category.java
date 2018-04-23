package com.datumize.dtos;

public class Category {

	private int categoryId;
	private String name;
	private String descr;
	private Department department;

	public Department getDepartment() {
		return department;
	}

	public Category(int categoryId, String aName, String aDescr, Department department) {
		this.categoryId = categoryId;
		this.name = aName;
		this.descr = aDescr;
		this.department = department;
	}

	public int getCategoryId() {
		return this.categoryId;
	}

	public String getName() {
		return this.name;
	}

	public String getDescr() {
		return this.descr;
	}

}
