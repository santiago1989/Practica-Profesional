package com.gestor.backend.dto;

import org.hibernate.Criteria;

import com.gestor.backend.util.SessionSingletion;
import com.gestor.common.util.Utils;
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
		addEqualInteger("id",!Utils.isNullOrNoDigit(code)?Integer.parseInt(code):0,criteria);
		addLike("description",description,criteria);
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
