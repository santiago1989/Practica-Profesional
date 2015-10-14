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
	
	public static PopupType lookUp(String description){
		for (PopupType type : PopupType.values()) {
			if(type.description.equals(description)){
				return type;
			}
		}return null;
	}
}