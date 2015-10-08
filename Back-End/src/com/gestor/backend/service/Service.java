package com.gestor.backend.service;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;

public interface Service {
	
	void guardar(Object object);
	
	void actualizar(Object object);
	
	void eliminar(Object object);
	
	Object get(Class<?> claz,Serializable id);

	List<?> buscar(Class<?> claz,List<Criterion> filtros);
	
	<T> List<T> findAll(Class<T> claz);
}
