package com.gestor.web.seguridad;

import java.util.Set;


public class Rol{

	private String code;
	
	private String nombre;
	
	private Set<Privilegio> privilegios;

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

	public Set<Privilegio> getPrivilegios() {
		return privilegios;
	}

	public void setPrivilegios(Set<Privilegio> privilegios) {
		this.privilegios = privilegios;
	}

	@Override
	public String toString() {
		return nombre;
	}
}
