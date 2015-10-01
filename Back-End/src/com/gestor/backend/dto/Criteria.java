package com.gestor.backend.dto;

public interface Criteria<T> {
	
	void build(T object);
	
}
