package com.gestor.backend.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.gestor.backend.dao.DAO;
import com.gestor.backend.dao.impl.DAOImpl;
import com.gestor.backend.service.Service;
import com.gestor.web.seguridad.Rol;
import com.gestor.web.seguridad.Usuario;


public class ServiceImpl implements Service{

	private DAO dao;

	private static Set<Rol> roles = new HashSet<Rol>(3);
	
	private static List<Usuario> usuarios = new ArrayList<Usuario>();
	
	static{
		roles.add(new Rol("A","Administrativo"));
		roles.add(new Rol("R","Responsable"));
		roles.add(new Rol("S","Superusuario"));
		
		usuarios.add(new Usuario(101,"river","santiago","apellido","34436430","santiagohernangonzalez@gmail.com", roles,"42428921",true));
	}
	
	public ServiceImpl() {
		this.dao = new DAOImpl();
		loadCache();
	}
	
	private void loadCache() {
		for (Rol rol : roles) {
			guardar(rol);
		}
//		for (Usuario usuario : usuarios) {
//			guardar(usuario);
//		}
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
	public <T> Collection<T> buscar(Class<T> claz,List<Criterion> filtros) {
		return dao.buscar(claz,filtros);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findAll(Class<T> claz){
		return (List<T>) buscar(claz, new ArrayList<Criterion>());
	}
	
	public <T> Collection<T> findIds(Class<T> claz,String ids){
		List<Criterion> criterionList = new ArrayList<Criterion>();
		//PARA BUSCAR POR IDS
		criterionList.add(Restrictions.in("code",ids.split(",")));
		return buscar(claz, criterionList);
	}
}
