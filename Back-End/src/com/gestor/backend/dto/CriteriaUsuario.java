package com.gestor.backend.dto;

import com.gestor.web.seguridad.Usuario;


public class CriteriaUsuario implements Criteria<Usuario>{
	
	private String legajo;
	
	private String nombre;
	
	private String apellido;

	public String getLegajo() {
		return legajo;
	}

	public void setLegajo(String legajo) {
		this.legajo = legajo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	@Override
	public void build(Usuario object) {
		// TODO Auto-generated method stub
		
	}
}
