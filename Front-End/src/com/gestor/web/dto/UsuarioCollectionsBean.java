package com.gestor.web.dto;

import java.util.List;

import com.gestor.web.seguridad.Rol;


public class UsuarioCollectionsBean extends CollectionsBean {
	
	private List<Rol> roles;
	
	public UsuarioCollectionsBean(List<Rol> roles){
		this.roles = roles;
	}

	public List<Rol> getRoles() {
		return roles;
	}

}
