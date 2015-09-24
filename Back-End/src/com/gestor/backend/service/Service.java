package com.gestor.backend.service;

import java.util.List;

import com.gestor.backend.dto.Filtro;
import com.gestor.common.interfaces.Identificable;

public interface Service {
	
	void guardar(Identificable object);
	
	void actualizar(Identificable object);
	
	void eliminar(Identificable object);
	
	Identificable get(Class claz,int id);

	List buscar(Class claz,List<Filtro> filtros);
	
}
