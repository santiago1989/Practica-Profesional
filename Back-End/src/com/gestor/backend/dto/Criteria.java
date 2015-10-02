package com.gestor.backend.dto;

import java.util.List;

public interface Criteria<T> {
	
	void build(T object);
	
	List<Filtro> getFiltros();
	
}
