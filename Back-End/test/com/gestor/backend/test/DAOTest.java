package com.gestor.backend.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;

import com.gestor.backend.dao.impl.DAOImpl;
import com.gestor.backend.dto.CriteriaUsuario;
import com.gestor.backend.service.Service;
import com.gestor.backend.service.impl.ServiceImpl;
import com.gestor.backend.util.SessionSingletion;
import com.gestor.common.enums.RolType;
import com.gestor.web.seguridad.Rol;
import com.gestor.web.seguridad.Usuario;

public class DAOTest {

	Service service;
	
	Usuario usuario;
	
	@Before
	public void prepare(){
		service = new ServiceImpl();
		
		usuario = new Usuario();
		usuario.setNombre("tato");
		usuario.setApellido("gonzalez");

	}
	
	@Test
	public void testUsuarios() {
		try{
			service.guardar(usuario);
			assertTrue(service.get(Usuario.class,usuario.getLegajo()) != null);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCriteria(){
		CriteriaUsuario usuarioCriteria = new CriteriaUsuario();
		usuarioCriteria.setApellido("gonzalez");
		try{
			service.guardar(usuario);
			assertEquals(service.buscar(Usuario.class,usuarioCriteria.getCriteria()).size(),1);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testBuscar(){
		assertFalse(service.findAll(Rol.class).isEmpty());
		CriteriaUsuario usuarioAdministrativo = new CriteriaUsuario(RolType.ADMINISTRATIVO.getCode());
		assertFalse(service.buscar(Usuario.class,usuarioAdministrativo.getCriteria()).isEmpty());
	}
	
	@Test
	public void testSQLQuery(){
		DAOImpl dao = new DAOImpl();
		List<Integer> list = dao.sqlQuery("select U.legajo from USUARIOS U INNER JOIN USUARIOSROLES UR ON U.legajo = UR.legajousuario where UR.idrol = 'A'","legajo",Hibernate.INTEGER);
		assertFalse(list.isEmpty());
		List list2 = (List) service.findIds(Usuario.class,"legajo",list.toArray());
		assertFalse(list2.isEmpty());
	}
	
	@Test
	public void testRoles(){
		List<String> roles = new ArrayList<String>();
		roles.add("A");
		roles.add("R");
		roles.add("S");
		Criteria criteria = SessionSingletion.getInstance().getSession().createCriteria(Usuario.class);
		criteria.createAlias("roles","rol");
		criteria.add(Restrictions.in("rol.code",roles));
		List list = criteria.list();
		assertFalse(list.isEmpty());
	}

}
