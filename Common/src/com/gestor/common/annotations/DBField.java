package com.gestor.common.annotations;

import com.gestor.common.enums.SQLType;

public @interface DBField {

	public String name();
	
	public SQLType sqlType();	
}
