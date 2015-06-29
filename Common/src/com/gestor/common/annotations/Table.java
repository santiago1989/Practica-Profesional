package com.gestor.common.annotations;

import com.gestor.common.enums.Schema;

public @interface Table {
	
	public String name();
	
	public Schema schema();
}
