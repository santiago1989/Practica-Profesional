package com.gestor.web.dto;

import java.util.List;

import org.hibernate.collection.PersistentSet;


public class UsuarioCollectionsBean extends CollectionsBean {
	
	private List roles;
	
	public UsuarioCollectionsBean(List roles){
		this.roles = roles;
	}

	public List getRoles() {
		return roles;
	}

}
