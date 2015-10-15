package com.gestor.entidades;

import com.gestor.common.annotations.RequestMapped;

public class TipoIncidencia {

	private Integer id;
	
	@RequestMapped(regexp="[A-z]+")
	private String nombre;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
