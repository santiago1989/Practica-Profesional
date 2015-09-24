package com.gestor.backend.service.impl;

import java.util.List;

import com.gestor.backend.dao.DAO;
import com.gestor.backend.dao.impl.DAOImpl;
import com.gestor.backend.dto.Filtro;
import com.gestor.backend.service.Service;
import com.gestor.common.interfaces.Identificable;


public class ServiceImpl implements Service{

	private DAO dao;
	
	public ServiceImpl() {
		this.dao = new DAOImpl();
	}
	
	@Override
	public void guardar(Identificable entidad) {
		dao.guardar(entidad);
	}

	@Override
	public void actualizar(Identificable entidad) {
		dao.guardar(entidad);
	}

	@Override
	public void eliminar(Identificable entidad) {
		dao.guardar(entidad);
	}

	@Override
	public Identificable get(Class claz, int id) {
		return dao.get(claz,id);
	}

	@Override
	public List buscar(Class claz,List<Filtro> filtros) {
		return dao.buscar(null);//FIXME ARREGLAR, se puso el null para que compile
	}
		
}
