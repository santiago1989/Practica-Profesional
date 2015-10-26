package com.gestor.backend.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;

import com.gestor.common.interfaces.Identificable;

public interface Service {
	
	void guardar(Object object);
	
	void actualizar(Object object);
	
	void eliminar(Identificable object);
	
	<T> T get(Class<T> claz,Serializable id);

	List buscar(Class claz,Criteria criteria);
	
	List findAll(Class claz);
	
	<T> Collection<T> findIds(Class<T> claz,String idName,String ids);
	
	<T> Collection<T> findIds(Class<T> claz,String idName,Object[] ids);
}
