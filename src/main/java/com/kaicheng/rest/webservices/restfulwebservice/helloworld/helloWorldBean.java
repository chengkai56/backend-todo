package com.kaicheng.rest.webservices.restfulwebservice.helloworld;

public class helloWorldBean {

	private String message;

	public helloWorldBean(String message) {
		// TODO Auto-generated constructor stub
		this.message = message;
	}
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "helloWorldBean [message=" + message + "]";
	}

	
}
