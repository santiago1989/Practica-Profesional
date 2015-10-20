package com.gestor.backend.dto;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;

import com.gestor.common.util.Utils;
import com.gestor.web.seguridad.Usuario;


public class CriteriaUsuario extends BaseCriteria{
	
	private String legajo;
	
	private String nombre;
	
	private String apellido;
	
	private String rol;

	public CriteriaUsuario(){
	}

	public CriteriaUsuario(String rol){
		this.rol = rol;
	}
	
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

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@Override
	public List<Criterion> getFiltros() {
		criteriosList = new ArrayList<Criterion>();
		addEqualInteger("legajo",Utils.isNullOrEmpty(legajo)? 0:Integer.valueOf(legajo));
		addLike("nombre",nombre);
		addLike("apellido",apellido);
		addIn("rol.code",rol);
		return criteriosList;
	}
	
	@Override
	public Class<?> getClaz() {
		return Usuario.class;
	}
}
