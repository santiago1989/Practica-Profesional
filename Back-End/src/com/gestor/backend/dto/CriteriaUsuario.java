package com.gestor.backend.dto;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;

import com.gestor.web.seguridad.Usuario;


public class CriteriaUsuario extends BaseCriteria{
	
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
	public List<Criterion> getFiltros() {
		criteriosList = new ArrayList<Criterion>();
		addEqualInteger("legajo",legajo == null? 0:Integer.valueOf(legajo));
		addLike("nombre",nombre);
		addLike("apellido",apellido);
		return criteriosList;
	}
	
	@Override
	public Class<?> getClaz() {
		return Usuario.class;
	}
}
