package com.gestor.backend.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;

public interface Service {
	
	void guardar(Object object);
	
	void actualizar(Object object);
	
	void eliminar(Object object);
	
	Object get(Class<?> claz,Serializable id);

	List buscar(Class claz,Criteria criteria);
	
	List findAll(Class claz);
	
	<T> Collection<T> findIds(Class<T> claz,String idName,String ids);
	
	<T> Collection<T> findIds(Class<T> claz,String idName,Object[] ids);
}
