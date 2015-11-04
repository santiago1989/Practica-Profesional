package com.gestor.backend.dto;

import org.hibernate.Criteria;

import com.gestor.backend.util.SessionSingletion;
import com.gestor.entidades.TipoIncidencia;

public class CriteriaTipoIncidencia extends BaseCriteria {

	private String code;
	
	private String description;
	
	@Override
	public Class<?> getClaz() {
		return null;
	}

	@Override
	public Criteria getCriteria() {
		Criteria criteria = SessionSingletion.getInstance().getSession().createCriteria(TipoIncidencia.class);
		addLike("code",code.concat("%"),criteria);
		addLike("description",description.concat("%"),criteria);
		return criteria;
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
