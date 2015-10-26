package com.gestor.backend.dto;

import org.hibernate.Criteria;

import com.gestor.backend.util.SessionSingletion;
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
	public Criteria getCriteria() {
		Criteria criteria = SessionSingletion.getInstance().getSession().createCriteria(Usuario.class);
		addEqualInteger("legajo",Utils.isNullOrEmpty(legajo)? 0:Integer.valueOf(legajo),criteria);
		addLike("nombre",nombre,criteria);
		addLike("apellido",apellido,criteria);
		if(!Utils.isNullOrEmpty(rol)){
			addAliasFilter(criteria,"roles","rol","code",rol.split(","));
		}
		addEqual("estado",Boolean.TRUE, criteria);
		return criteria;
	}
	
	@Override
	public Class<?> getClaz() {
		return Usuario.class;
	}
}
