package com.gestor.web.seguridad;

import com.gestor.common.interfaces.Identificable;

public class Rol implements Identificable{

	private int code;
	
	private String acction;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getAcction() {
		return acction;
	}

	public void setAcction(String acction) {
		this.acction = acction;
	}

	@Override
	public Integer getId() {
		return getCode();
	}
}
