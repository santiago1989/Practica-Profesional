package com.gestor.web.seguridad;

import java.util.List;


public class Rol{

	private String code;
	
	private String nombre;
	
	private List<Privilegio> privilegios;
	
	public Rol(String code, String nombre) {
		super();
		this.code = code;
		this.nombre = nombre;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Privilegio> getPrivilegios() {
		return privilegios;
	}

	public void setPrivilegios(List<Privilegio> privilegios) {
		this.privilegios = privilegios;
	}
}
