package com.gestor.backend.dto;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Criterion;


public class CriteriaIncidencia extends BaseCriteria {
	
	private Integer numero;
	private String owner;
	private String responsable;
	private String estado;
	private String prioridad;
	private String tipo;
	private String detalle;
	private String titulo;
	private Date fechaCreacion;
	private Date fechaModificacion;
	
	@Override
	public List<Criterion> getFiltros() {
		addLike("numero",String.format("%s%",numero == null? "":numero));
		addLike("owner",String.format("%s%",owner == null? "":owner));
		addLike("responsable",String.format("%s%",responsable == null? "":responsable));		
		addLike("estado",String.format("%s",estado == null? "":estado));
		addLike("prioridad",String.format("%s",prioridad == null? "":prioridad));	
		addLike("tipo",String.format("%s",tipo == null? "":tipo));
		addLike("detalle",String.format("%%s%",detalle == null? "":detalle));
		addLike("titulo",String.format("%%s%",titulo == null? "":titulo));
		addLike("fechaCreacion",String.format("%s",fechaCreacion == null? "":fechaCreacion));
		addLike("fechaModificacion",String.format("%s",fechaModificacion == null? "":fechaModificacion));
		return criteriosList;
	}

}
