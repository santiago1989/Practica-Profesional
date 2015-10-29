package com.gestor.web.dto;

import com.gestor.backend.service.Service;


public abstract class CollectionsBean {
	
	protected Service service;
	
	protected CollectionsBean(Service service){
		this.service = service;
	}
	
	public abstract void refreshCollections();
	
}
