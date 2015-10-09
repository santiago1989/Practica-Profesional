package com.gestor.backend.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;

public interface DAO {
	
	void guardar(Object entidad);

	Object get(Class<?> claz,Serializable id);
	
	<T> List<T> buscar(Class<T> claz,List<Criterion> filtros);
}
