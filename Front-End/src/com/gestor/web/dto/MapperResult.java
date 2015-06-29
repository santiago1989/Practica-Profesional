package com.gestor.web.dto;

import java.util.ArrayList;
import java.util.List;

public class MapperResult<T> {
	
	private T entity;

	private List<String> errorMessages;
	
	public MapperResult(T entity){
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
	
	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}
}
