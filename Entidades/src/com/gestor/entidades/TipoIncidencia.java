package com.gestor.entidades;

import com.gestor.common.annotations.RequestMapped;
import com.gestor.common.dto.MailMessage;
import com.gestor.common.interfaces.Identificable;

public class TipoIncidencia implements Identificable {

	@RequestMapped(regexp="[0-9]+",required=false)
	private Integer id;
	
	@RequestMapped(regexp="[A-z·ÈÌÛ˙¡…Õ”⁄—Ò ]+")
	private String nombre;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getIdString(){
		return String.valueOf(id);
	}
	@Override
	public void copyFrom(Object object) {
		TipoIncidencia tipoIncidencia = (TipoIncidencia)object;
		this.nombre = tipoIncidencia.nombre;
	}
	@Override
	public void setEstado() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Boolean getEstadoAlta() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public MailMessage getMailMessage() {
		// TODO Auto-generated method stub
		return null;
	}
}
