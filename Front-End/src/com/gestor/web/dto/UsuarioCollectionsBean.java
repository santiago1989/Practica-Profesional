package com.gestor.web.dto;

import java.util.List;

import com.gestor.backend.service.Service;
import com.gestor.web.seguridad.Rol;


public class UsuarioCollectionsBean extends CollectionsBean {
	
	private List roles;
	
	public UsuarioCollectionsBean(Service service){
		super(service);
		this.service = service;
		this.roles = service.findAll(Rol.class);
	}

	public List getRoles() {
		return roles;
	}

	@Override
	public void refreshCollections() {
//		DO NOTHING
	}

}
