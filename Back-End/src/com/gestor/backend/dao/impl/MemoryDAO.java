package com.gestor.backend.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.gestor.backend.dao.DAO;
import com.gestor.common.collections.predicate.IdentificablePredicate;
import com.gestor.common.collections.util.CollectionUtils;
import com.gestor.common.interfaces.Criteria;
import com.gestor.common.interfaces.Identificable;

public class MemoryDAO<T extends Identificable> implements DAO<T> {

	private List<T> table;
	
	public MemoryDAO(){
		init();
	}
	
	private void init(){
		table = new ArrayList<T>();
	}
	
	@Override
	public void guardar(T entidad) {
		table.add(entidad);
	}

	@Override
	public T get(Identificable entidad) {
		return CollectionUtils.find(table, new IdentificablePredicate<T>(entidad));
	}

	@Override
	public List<T> buscar(Criteria criteria) {
		return null;
	}

}
