package com.gestor.web.seguridad;

import com.gestor.common.annotations.Id;


public class Privilegio{
	
	@Id
	private Integer code;
	
	private String description;
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
