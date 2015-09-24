package com.gestor.backend.service;

import com.gestor.backend.service.impl.ServiceImpl;
import com.gestor.web.seguridad.Usuario;

public class AuthService {
	
	private Service service = new ServiceImpl();
	
	public boolean isCredentialValid(Integer legajo,String password){
		Usuario usuario = (Usuario) service.get(Usuario.class,legajo);
		return (usuario == null )?false:usuario.getContraseña().equals(password);
	}
}
