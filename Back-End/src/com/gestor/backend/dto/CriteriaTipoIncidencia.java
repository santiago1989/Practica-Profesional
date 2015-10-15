package com.gestor.backend.dto;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;

public class CriteriaTipoIncidencia extends BaseCriteria {

	private String code;
	
	private String description;
	
	@Override
	public Class<?> getClaz() {
		return null;
	}

	@Override
	public List<Criterion> getFiltros() {
		criteriosList = new ArrayList<Criterion>();
		addLike("code",code.concat("%"));
		addLike("description",description.concat("%"));
		return criteriosList;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
