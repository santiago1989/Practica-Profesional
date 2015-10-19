package com.gestor.backend.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.collection.PersistentSet;
import org.hibernate.criterion.Criterion;

public interface DAO {
	
	void guardar(Object entidad);

	Object get(Class<?> claz,Serializable id);
	
	List buscar(Class claz,List<Criterion> filtros);
}
