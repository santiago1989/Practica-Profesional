package com.gestor.backend.dto;

import java.util.List;

import com.gestor.web.seguridad.Usuario;


public class CriteriaUsuario implements Criteria<Usuario>{
	
	private Integer legajo;
	
	private String nombre;
	
	private String apellido;

	public Integer getLegajo() {
		return legajo;
	}

	public void setLegajo(Integer legajo) {
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
		this.legajo = object.getLegajo();
		this.nombre = object.getNombre();
		this.apellido = object.getApellido();
	}

	@Override
	public List<Filtro> getFiltros() {
		// TODO Auto-generated method stub
		return null;
	}
}
