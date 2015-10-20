package com.gestor.entidades;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;

import com.gestor.common.annotations.RequestMapped;
import com.gestor.web.seguridad.Usuario;

public class Incidencia {

	private int numero;
	
	@RequestMapped(regexp="[0-9]+",customType=true,customTypeClass=Usuario.class,required=false)
	private Usuario owner;

	@RequestMapped(regexp="[0-9]+",customType=true,customTypeClass=Usuario.class,required=false)
	private Usuario responsable;
	
	@RequestMapped(regexp="[ARCS]",customType=true,customTypeClass=EstadoIncidencia.class)
	private EstadoIncidencia estado;
	
	@RequestMapped(regexp="[AMB]",customType=true,customTypeClass=PrioridadIncidencia.class)
	private PrioridadIncidencia prioridad;

	@RequestMapped(regexp="[A-Za-z]",customType=true,customTypeClass=TipoIncidencia.class)
	private TipoIncidencia tipoIncidencia;
	
	@RequestMapped(regexp="[A-Za-z]+")
	private String detalle;

	@RequestMapped(regexp="[A-Za-z]+")
	private String titulo;
		
	private Set<Adjunto> adjuntos;
	private Set<Nota> notas;
		
	private Date fechaCreacion;
	private Date fechaModificacion;

	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public Usuario getOwner() {
		return owner;
	}
	public void setOwner(Usuario owner) {
		this.owner = owner;
	}
	public Usuario getResponsable() {
		return responsable;
	}
	public void setResponsable(Usuario responsable) {
		this.responsable = responsable;
	}
	public EstadoIncidencia getEstado() {
		return estado;
	}
	public void setEstado(EstadoIncidencia estado) {
		this.estado = estado;
	}
	public PrioridadIncidencia getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(PrioridadIncidencia prioridad) {
		this.prioridad = prioridad;
	}
	public TipoIncidencia getTipoIncidencia() {
		return tipoIncidencia;
	}
	public void setTipoIncidencia(TipoIncidencia tipoIncidencia) {
		this.tipoIncidencia = tipoIncidencia;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Set<Adjunto> getAdjuntos() {
		return adjuntos;
	}
	public void setAdjuntos(Set<Adjunto> adjuntos) {
		this.adjuntos = adjuntos;
	}
	public Set<Nota> getNotas() {
		return notas;
	}
	public void setNotas(Set<Nota> notas) {
		this.notas = notas;
	}
	public Date getFechaCreacion() {
		return fechaCreacion == null ? GregorianCalendar.getInstance().getTime():fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Date getFechaModificacion() {
		return fechaModificacion == null ? GregorianCalendar.getInstance().getTime():fechaModificacion;
	}
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
}
