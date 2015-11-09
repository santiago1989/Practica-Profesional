package com.gestor.web.seguridad;

import java.util.Arrays;
import java.util.Set;

import com.gestor.common.annotations.RequestMapped;
import com.gestor.common.annotations.Table;
import com.gestor.common.dto.MailMessage;
import com.gestor.common.enums.Schema;
import com.gestor.common.interfaces.Identificable;
import com.gestor.common.util.Constants;
import com.gestor.common.util.Utils;

@Table(name="T_USUARIO",schema=Schema.SEGURIDAD)
public class Usuario implements Identificable{
	
	@RequestMapped(regexp="[0-9]+",required=false)
	private Integer legajo;

	@RequestMapped(regexp="[A-Za-z0-9]+")
	private String contrasena;
	
	@RequestMapped(regexp="[A-z·ÈÌÛ˙¡…Õ”⁄—Ò ]+")
	private String nombre;
	
	@RequestMapped(regexp="[A-z·ÈÌÛ˙¡…Õ”⁄—Ò ]+")
	private String apellido;

	@RequestMapped(regexp="[0-9]{8}")
	private String dni;

	@RequestMapped(regexp="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9]+\\.[A-Za-z]{2,6}$")
	private String correo;
	
	@RequestMapped(regexp="[ARS]",customType=true,customTypeClass=Rol.class,idName="code")
	private Set<Rol> roles;
	
	@RequestMapped(regexp="[0-9]{8,14}",required=false)
	private String telefono;

	private boolean estado = true;

	public Usuario(){
	}
	
	public Usuario(int legajo, String contrasena, String nombre,
			String apellido, String dni, String correo, Set<Rol> roles,
			String telefono, boolean estado) {
		super();
		this.legajo = legajo;
		this.contrasena = contrasena;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.correo = correo;
		this.roles = roles;
		this.telefono = telefono;
		this.estado = estado;
	}

	public Integer getId(){
		return getLegajo();
	}

	public Integer getLegajo() {
		return legajo;
	}
	public void setLegajo(Integer legajo) {
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
	public String getRolesDescription(){
		return Arrays.toString(roles.toArray());
	}
	
	public String getRolesValues(){
		StringBuilder builder = new StringBuilder();
		for (Rol rol : roles) {
			builder.append(rol.getCode());
			builder.append(",");
		}
		return builder.toString();
	}

	@Override
	public String toString() {
		if(Utils.isNullOrEmpty(nombre)){
			return nombre;
		}else{
			return "";
		}
	}

	@Override
	public void copyFrom(Object object) {
		Usuario usuario = (Usuario)object;
		this.nombre = usuario.nombre;
		this.apellido = usuario.apellido;
		this.telefono = usuario.telefono;
		this.dni = usuario.dni;
		this.correo = usuario.correo;
		this.roles = usuario.roles;
		this.contrasena = usuario.contrasena;
	}

	@Override
	public void setEstado() {
		this.estado = Boolean.FALSE;
	}

	@Override
	public Boolean getEstadoAlta() {
		return isEstado();
	}

	@Override
	public MailMessage getMailMessage() {
		String body = "Su usuario ha sido creado de forma exitosa, y puede acceder al mismo, ingresando con su legajo: ".concat(String.valueOf(legajo));
		String subject = "Creacion de usuario";
		return	new MailMessage(Constants.FROM_ADDRESS, 
						new String[]{this.correo}, 
						subject, 
						body);
	}
}
