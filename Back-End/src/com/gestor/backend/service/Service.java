package com.gestor.backend.service;

import java.util.List;

import com.gestor.backend.dto.Filtro;

public interface Service<T> {
	
	void guardar(T object);
	
	void actualizar(T object);
	
	void eliminar(T object);
	
	T get(Class<T> claz,int id);

	List<T> buscar(Class<T> claz,List<Filtro> filtros);
	
}
