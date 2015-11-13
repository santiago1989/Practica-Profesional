package com.gestor.entidades;

import java.util.Date;

import com.gestor.common.dto.MailMessage;
import com.gestor.common.interfaces.Identificable;
import com.gestor.web.seguridad.Usuario;

public class Nota implements Identificable{
	
	private Integer id;

	private String detalle;

	private Incidencia incidencia;

	private Usuario usuario;
	
	private Date fechaPublicacion;
	
	private boolean estado = Boolean.TRUE;
	
	public Nota(){
	}
	
	public Nota(String detalle, Usuario usuario) {
		super();
		this.detalle = detalle;
		this.usuario = usuario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public Incidencia getIncidencia() {
		return incidencia;
	}

	public void setIncidencia(Incidencia incidencia) {
		this.incidencia = incidencia;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public void copyFrom(Object object) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setEstado() {
		estado = Boolean.FALSE;
	}

	@Override
	public Boolean getEstadoAlta() {
		return isEstado();
	}

	@Override
	public MailMessage getMailMessageCreate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MailMessage getMailMessageUpdate() {
		// TODO Auto-generated method stub
		return null;
	}
}
