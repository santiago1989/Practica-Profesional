package com.gestor.backend.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.gestor.backend.dto.CriteriaUsuario;
import com.gestor.backend.service.Service;
import com.gestor.backend.service.impl.ServiceImpl;
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
			assertEquals(service.buscar(Usuario.class,usuarioCriteria.getFiltros()).size(),1);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
