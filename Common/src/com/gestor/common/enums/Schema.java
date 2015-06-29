package com.gestor.common.enums;

public enum Schema {
	
	SEGURIDAD("SEGURIDAD"),
	APLICACION("APLICACION");
	
	private String description;
	
	private Schema(String description){
		this.description = description;
	}
	
	public String getDescription(){
		return description;
	}
}
