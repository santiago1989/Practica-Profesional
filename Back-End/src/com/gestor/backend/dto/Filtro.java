package com.gestor.backend.dto;

import org.hibernate.criterion.Criterion;

public class Filtro {
	
	private String fieldName;

	private Criterion criterion;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Criterion getCriterion() {
		return criterion;
	}

	public void setCriterion(Criterion criterion) {
		this.criterion = criterion;
	}
}
