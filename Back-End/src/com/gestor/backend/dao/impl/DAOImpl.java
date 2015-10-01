package com.gestor.backend.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.gestor.backend.dao.DAO;
import com.gestor.backend.dto.Criteria;
import com.gestor.backend.util.SessionSingletion;
import com.gestor.common.interfaces.Identificable;

public class DAOImpl implements DAO {

	@Override
	public void guardar(Identificable entidad) {
		Session session = SessionSingletion.getInstance().getSession();
		session.saveOrUpdate(entidad);
	}

	@Override
	public Identificable get(Class claz,Integer id) {
		Session session = SessionSingletion.getInstance().getSession();
		return (Identificable) session.get(claz,id);
	}

	@Override
	public List buscar(Criteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}
}