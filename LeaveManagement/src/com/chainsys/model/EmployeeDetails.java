package com.chainsys.model;

class EmployeeDetails {
	private int userId;
	private String name;
	private String password;

	public EmployeeDetails(int userId, String name, String password) {
		this.userId = userId;
		this.name = name;
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}





