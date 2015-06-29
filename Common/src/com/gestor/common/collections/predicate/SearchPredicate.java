package com.gestor.common.collections.predicate;

import org.apache.commons.collections4.Predicate;

public class SearchPredicate<T> implements Predicate<T> {

	private T filtros;
	private Class claz;
	
	public SearchPredicate(T filtros) {
		this.filtros = filtros;
		claz = filtros.getClass();
	}
	
	@Override
	public boolean evaluate(T object) {
		// TODO Auto-generated method stub
		return false;
	}
}
