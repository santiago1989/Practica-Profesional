package com.gestor.web.seguridad;

import com.gestor.common.annotations.Id;


public class Privilegio{
	
	@Id
	private String code;
	
	private String description;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
