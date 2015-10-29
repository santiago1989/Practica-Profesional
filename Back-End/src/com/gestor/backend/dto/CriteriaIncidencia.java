package com.gestor.backend.dto;

import org.hibernate.Criteria;

import com.gestor.backend.util.SessionSingletion;
import com.gestor.common.util.Utils;
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
	public Criteria getCriteria() {
		Criteria criteria = SessionSingletion.getInstance().getSession().createCriteria(Incidencia.class);
		addEqualInteger("numero",!Utils.isNullOrEmpty(numero)?Integer.parseInt(numero):0,criteria);
		addEqualInteger("owner.legajo",!Utils.isNullOrEmpty(owner)?Integer.parseInt(owner):0,criteria);
		addEqualInteger("responsable.legajo",!Utils.isNullOrEmpty(responsable)?Integer.parseInt(responsable):0,criteria);
		addEqualInteger("estado.id",!Utils.isNullOrEmpty(estado)?Integer.parseInt(estado):0,criteria);
		addEqualInteger("prioridad.id",!Utils.isNullOrEmpty(prioridad)?Integer.parseInt(prioridad):0,criteria);	
		addEqualInteger("tipoIncidencia.id",!Utils.isNullOrEmpty(tipo)?Integer.parseInt(tipo):0,criteria);
		addLike("detalle",detalle,criteria);
		addLike("titulo",titulo,criteria);
		addEqual("fechaCreacion",fechaCreacion,criteria);
		addEqual("fechaModificacion",fechaModificacion,criteria);
		return criteria;
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
