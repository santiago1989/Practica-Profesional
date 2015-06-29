package com.gestor.common.enums;

public enum SQLType {

	STRING("STRING"),
	NUMBER("NUMBER");
	
	private String description;
	
	private SQLType(String description){
		this.description = description;
	}
	
	public String getDescription(){
		return description;
	}
	
}
