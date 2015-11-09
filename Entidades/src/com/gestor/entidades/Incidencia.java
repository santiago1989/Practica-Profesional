package com.gestor.entidades;

import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import com.gestor.common.annotations.RequestMapped;
import com.gestor.common.dto.MailMessage;
import com.gestor.common.interfaces.Identificable;
import com.gestor.web.seguridad.Usuario;

public class Incidencia implements Identificable{

	@RequestMapped(regexp="[0-9]+",required=false)
	private Integer numero;
	
	@RequestMapped(regexp="[0-9]+",customType=true,customTypeClass=Usuario.class,required=false,idName="legajo")
	private Usuario owner;

	@RequestMapped(regexp="[0-9]+",customType=true,customTypeClass=Usuario.class,required=false,idName="legajo")
	private Usuario responsable;
	
	@RequestMapped(regexp="[0-9]",customType=true,customTypeClass=EstadoIncidencia.class)
	private EstadoIncidencia estado;
	
	@RequestMapped(regexp="[0-9]",customType=true,customTypeClass=PrioridadIncidencia.class)
	private PrioridadIncidencia prioridad;

	@RequestMapped(regexp="[0-9]",customType=true,customTypeClass=TipoIncidencia.class)
	private TipoIncidencia tipoIncidencia;
	
	@RequestMapped(regexp="[A-Za-z0-9 '-.:]+")
	private String detalle;

	@RequestMapped(regexp="[A-Za-z0-9 '-.:]+")
	private String titulo;
		
	private Set<Adjunto> adjuntos;
	private Set<Nota> notas;
		
	private Date fechaCreacion;
	private Date fechaModificacion;

	public Incidencia(){
		this.adjuntos = new HashSet<Adjunto>();
		this.notas = new HashSet<Nota>();
	}
	
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
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
	@Override
	public Integer getId() {
		return numero;
	}
	@Override
	public void copyFrom(Object object) {
		Incidencia incidencia = (Incidencia) object;
		this.titulo = incidencia.titulo;
		this.detalle = incidencia.detalle;
		this.responsable = incidencia.responsable;
		this.estado = incidencia.estado;
		this.prioridad = incidencia.prioridad;
		this.estado = incidencia.estado;
		this.tipoIncidencia = incidencia.tipoIncidencia;
		this.fechaModificacion = GregorianCalendar.getInstance().getTime();
		
	}
	@Override
	public void setEstado() {
		
	}
	
	public void addNota(Nota comentario){
		comentario.setIncidencia(this);
		notas.add(comentario);
	}
	
	public void removeNota(Nota nota){
		notas.remove(nota);
	}
	
	public void addAdjunto(Adjunto adjunto){
		adjuntos.add(adjunto);
	}
	
	public void addAdjuntos(Collection<Adjunto> adjuntos){
		this.adjuntos.addAll(adjuntos);
	}

	@Override
	public Boolean getEstadoAlta() {
		return null;
	}

	@Override
	public MailMessage getMailMessage() {
		// TODO Auto-generated method stub
		return null;
	}
}
