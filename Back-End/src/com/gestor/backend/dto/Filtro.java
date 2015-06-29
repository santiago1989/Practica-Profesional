package com.gestor.backend.dto;

import com.gestor.common.annotations.DBField;

public class Filtro {
	
	private String fieldName;

	private DBField sqlField;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public DBField getSqlField() {
		return sqlField;
	}

	public void setSqlField(DBField sqlField) {
		this.sqlField = sqlField;
	}
}
