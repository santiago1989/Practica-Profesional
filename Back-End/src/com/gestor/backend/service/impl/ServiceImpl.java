package com.gestor.backend.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.collection.PersistentSet;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.gestor.backend.dao.DAO;
import com.gestor.backend.dao.impl.DAOImpl;
import com.gestor.backend.service.Service;


public class ServiceImpl implements Service{

	private DAO dao;
	
	
	public ServiceImpl() {
		this.dao = new DAOImpl();
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
	public List buscar(Class claz,List<Criterion> filtros) {
		return dao.buscar(claz,filtros);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List findAll(Class claz){
		return buscar(claz, new ArrayList<Criterion>());
	}
	
	public <T> Collection<T> findIds(Class<T> claz,String idName,String ids){
		return findIds(claz, idName, ids.split(","));
	}
	
	public <T> Collection<T> findIds(Class<T> claz,String idName,Object[] ids){
		List<Criterion> criterionList = new ArrayList<Criterion>();
		//PARA BUSCAR POR IDS
		criterionList.add(Restrictions.in(idName,ids));
		return buscar(claz, criterionList);
	}
}
