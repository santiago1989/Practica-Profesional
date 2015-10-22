package com.gestor.backend.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.gestor.backend.dao.DAO;
import com.gestor.backend.dao.impl.DAOImpl;
import com.gestor.backend.service.Service;
import com.gestor.backend.util.SessionSingletion;


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
	public List buscar(Class claz,Criteria criteria) {
		return dao.buscar(claz,criteria);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List findAll(Class claz){
		return buscar(claz,null);
	}
	
	public <T> Collection<T> findIds(Class<T> claz,String idName,String ids){
		return findIds(claz, idName, ids.split(","));
	}
	
	public <T> Collection<T> findIds(Class<T> claz,String idName,Object[] ids){
		Criteria criteria = SessionSingletion.getInstance().getSession().createCriteria(claz);
		criteria.add(Restrictions.in(idName,ids));
		return buscar(claz, criteria);
	}
}
