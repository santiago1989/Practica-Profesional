package com.gestor.web.dto;

import java.util.ArrayList;
import java.util.List;

import com.gestor.common.interfaces.Identificable;

public class MapperResult {
	
	private Identificable entity;

	private List<String> errorMessages;
	
	public MapperResult(Identificable entity){
		this.entity = entity;
		init();
	}

	public MapperResult(){		
		init();
	}
	
	private void init(){
		this.errorMessages = new ArrayList<String>();
	}
	
	public List<String> getErrorMessages() {
		return errorMessages;
	}

	public void addError(String message){
		this.errorMessages.add(message);
	}
	
	public Identificable getEntity() {
		return entity;
	}

	public void setEntity(Identificable entity) {
		this.entity = entity;
	}
}
