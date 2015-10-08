package com.gestor.backend.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;

import com.gestor.backend.dao.DAO;
import com.gestor.backend.dao.impl.DAOImpl;
import com.gestor.backend.service.Service;
import com.gestor.web.seguridad.Rol;


public class ServiceImpl implements Service{

	private DAO dao;

	private static List<Rol> roles = new ArrayList<Rol>(3);
	
	static{
		roles.add(new Rol("A","Administrativo"));
		roles.add(new Rol("R","Responsable"));
		roles.add(new Rol("S","Superusuario"));
	}
	
	public ServiceImpl() {
		this.dao = new DAOImpl();
		loadCache();
	}
	
	private void loadCache() {
		for (Rol rol : roles) {
			guardar(rol);
		}
	}

	@Override
	public void guardar(Object entidad) {
		dao.guardar(entidad);
	}

	@Override
	public void actualizar(Object entidad) {
		dao.guardar(entidad);
	}

	@Override
	public void eliminar(Object entidad) {
		dao.guardar(entidad);
	}

	@Override
	public Object get(Class<?> claz, Serializable id) {
		return dao.get(claz,id);
	}

	@Override
	public List<?> buscar(Class<?> claz,List<Criterion> filtros) {
		return dao.buscar(claz,filtros);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findAll(Class<T> claz){
		return (List<T>) buscar(claz, new ArrayList<Criterion>());
	}
}
