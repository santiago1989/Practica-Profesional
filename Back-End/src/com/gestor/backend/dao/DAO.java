package com.gestor.backend.dao;

import java.util.List;

import com.gestor.backend.dto.Criteria;
import com.gestor.common.interfaces.Identificable;

public interface DAO {
	
	void guardar(Identificable entidad);

	Identificable get(Class claz,Integer id);
	
	List buscar(Criteria criteria);
}
