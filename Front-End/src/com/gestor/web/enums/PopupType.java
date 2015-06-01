package com.gestor.web.enums;

public enum PopupType {
	
	INFORMATION("info"),CONFIRMATION("confirm"),ERROR("error");
	
	private String description;
	
	private PopupType(String description) {
		this.description = description;
	}
	
	public String getDescription(){
		return description;
	}
}