package com.gestor.entidades;

import com.gestor.common.annotations.RequestMapped;
import com.gestor.common.interfaces.Identificable;

public class TipoIncidencia implements Identificable {

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
	
	public String getIdString(){
		return String.valueOf(id);
	}
	@Override
	public void copyFrom(Object object) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setEstado() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Boolean getEstadoAlta() {
		// TODO Auto-generated method stub
		return null;
	}
}
