package com.gestor.web.seguridad;

import java.util.Set;

import com.gestor.common.annotations.RequestMapped;
import com.gestor.common.annotations.Table;
import com.gestor.common.enums.Schema;
import com.gestor.common.interfaces.Identificable;

@Table(name="T_USUARIO",schema=Schema.SEGURIDAD)
public class Usuario implements Identificable{
	
	private int legajo;

	@RequestMapped(regexp="[A-Za-z0-9]+")
	private String contrasena;
	
	@RequestMapped(regexp="[A-z]+")
	private String nombre;
	
	@RequestMapped(regexp="[A-z]+")
	private String apellido;

	@RequestMapped(regexp="[0-9]{8}")
	private String dni;

	@RequestMapped(regexp="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9]+\\.[A-Za-z]{2,6}$")
	private String correo;
	
	@RequestMapped(regexp="[0-2]",customType=true)
	private Set<Rol> roles;
	
	@RequestMapped(regexp="[0-9]{8,14}",required=false)
	private String telefono;

	private boolean estado = true;

	public Integer getId(){
		return getLegajo();
	}

	public int getLegajo() {
		return legajo;
	}
	public void setLegajo(int legajo) {
		this.legajo = legajo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public Set<Rol> getRoles() {
		return roles;
	}
	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}
