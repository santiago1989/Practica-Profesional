package com.gestor.backend.service;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;

import com.gestor.common.interfaces.Identificable;

public interface Service {
	
	void guardar(Identificable object);
	
	void actualizar(Identificable object);
	
	void eliminar(Identificable object);
	
	Object get(Class<?> claz,Serializable id);

	List<?> buscar(Class<?> claz,List<Criterion> filtros);
	
}
