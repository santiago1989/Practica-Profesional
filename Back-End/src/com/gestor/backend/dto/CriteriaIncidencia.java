package com.gestor.backend.dto;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;

import com.gestor.entidades.Incidencia;


public class CriteriaIncidencia extends BaseCriteria {
	
	private String numero;
	private String owner;
	private String responsable;
	private String estado;
	private String prioridad;
	private String tipo;
	private String detalle;
	private String titulo;
	private String fechaCreacion;
	private String fechaModificacion;
	
	@Override
	public List<Criterion> getFiltros() {
		criteriosList = new ArrayList<Criterion>();
		addEqualInteger("numero",numero == null ?Integer.parseInt(numero):0);
		addEqualInteger("owner",Integer.parseInt(owner));
		addEqualInteger("responsable",Integer.parseInt(responsable));
		addEqual("estado",estado);
		addEqual("prioridad",prioridad);	
		addEqual("tipo",tipo);
		addLike("detalle",detalle);
		addLike("titulo",titulo);
		addEqual("fechaCreacion",fechaCreacion);
		addEqual("fechaModificacion",fechaModificacion);
		return criteriosList;
	}

	@Override
	public Class<?> getClaz() {
		return Incidencia.class;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(String fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
}
