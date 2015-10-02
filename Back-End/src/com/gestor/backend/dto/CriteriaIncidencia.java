package com.gestor.backend.dto;

import java.util.Date;
import java.util.List;

import com.gestor.entidades.Incidencia;


public class CriteriaIncidencia implements Criteria<Incidencia> {
	
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
	public void build(Incidencia object) {
//		TODO validar si se va a usar o no
		this.numero = object.getNumero();		
		this.fechaModificacion = object.getFechaModificacion();
		this.fechaCreacion = object.getFechaCreacion();
	}

	@Override
	public List<Filtro> getFiltros() {
		// TODO Auto-generated method stub
		return null;
	}

}
