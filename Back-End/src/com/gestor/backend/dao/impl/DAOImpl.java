package com.gestor.backend.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

import com.gestor.backend.dao.DAO;
import com.gestor.backend.util.SessionSingletion;

public class DAOImpl implements DAO {

	@Override
	public void guardar(Object entidad) {
		Session session = SessionSingletion.getInstance().getSession();
		session.saveOrUpdate(entidad);
	}

	@Override
	public Object get(Class<?> claz,Serializable id) {
		Session session = SessionSingletion.getInstance().getSession();
		return session.get(claz,id);
	}

	@Override
	public <T> List<T> buscar(Class<T> claz,List<Criterion> filtros) {
		Session session = SessionSingletion.getInstance().getSession();
		Criteria criteria = session.createCriteria(claz);
		for (Criterion criterion : filtros) {
			criteria.add(criterion);
		}
		List<T> list = criteria.list();
		return list;
	}
}