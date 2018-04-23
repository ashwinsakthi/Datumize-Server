package com.datumize.dtos;

public class Department {

	private int deptId;
	private String name;
	private String descr;	

	public Department(int deptId, String aName, String aDescr) {
		this.deptId = deptId;
		this.name = aName;
		this.descr = aDescr;
	}

	public int getDeptId() {
		return this.deptId;
	}

	public String getName() {
		return this.name;
	}

	public String getDescr() {
		return this.descr;
	}

}
