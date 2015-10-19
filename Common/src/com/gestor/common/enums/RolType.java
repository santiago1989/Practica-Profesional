package com.gestor.common.enums;

public enum RolType {

	ADMINISTRATIVO("A"),
	RESPONSABLE("R"),
	SUPERUSUARIO("S");

	private String code;
	
	private RolType(String code){
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
