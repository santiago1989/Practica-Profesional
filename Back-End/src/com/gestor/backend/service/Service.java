package com.gestor.backend.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.Criterion;

public interface Service {
	
	void guardar(Object object);
	
	void actualizar(Object object);
	
	void eliminar(Object object);
	
	Object get(Class<?> claz,Serializable id);

	<T> Collection<T> buscar(Class<T> claz,List<Criterion> filtros);
	
	<T> List<T> findAll(Class<T> claz);
	
	<T> Collection<T> findIds(Class<T> claz,String ids);
}
