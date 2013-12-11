package com.example.nomnomgui;

public class Food {
	private String type;
	private String category;
	private int id;
	
	public Food(String type, String category, int id) {
		this.type = type;
		this.setCategory(category);
		this.id = id;
	}
	


	public Food() {
		// TODO Auto-generated constructor stub
	}



	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}

	
}
