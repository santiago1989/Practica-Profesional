package com.gestor.backend.dao;

import java.util.List;

import com.gestor.common.interfaces.Criteria;
import com.gestor.common.interfaces.Identificable;

public interface DAO<T> {
	
	void guardar(T entidad);

	T get(Identificable entidad);
	
	List<T> buscar(Criteria criteria);
}
