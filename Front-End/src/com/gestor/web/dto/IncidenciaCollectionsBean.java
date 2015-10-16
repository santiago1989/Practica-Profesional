package com.gestor.web.dto;

import java.util.List;

import com.gestor.entidades.EstadoIncidencia;
import com.gestor.entidades.PrioridadIncidencia;
import com.gestor.entidades.TipoIncidencia;
import com.gestor.web.seguridad.Usuario;

public class IncidenciaCollectionsBean extends CollectionsBean {
	
	private List<Usuario> owners;
	
	private List<Usuario> responsables;
	
	private List<TipoIncidencia> tiposIncidencia;
	
	private List<EstadoIncidencia> estadosIncidencia;
	
	private List<PrioridadIncidencia> prioridadIncidencia;
	
	public IncidenciaCollectionsBean(List<Usuario> owners,
			List<Usuario> responsables, List<TipoIncidencia> tiposIncidencia,
			List<EstadoIncidencia> estadosIncidencia,
			List<PrioridadIncidencia> prioridadIncidencia) {
		super();
		this.owners = owners;
		this.responsables = responsables;
		this.tiposIncidencia = tiposIncidencia;
		this.estadosIncidencia = estadosIncidencia;
		this.prioridadIncidencia = prioridadIncidencia;
	}

	public List<Usuario> getOwners() {
		return owners;
	}

	public void setOwners(List<Usuario> owners) {
		this.owners = owners;
	}

	public List<Usuario> getResponsables() {
		return responsables;
	}

	public void setResponsables(List<Usuario> responsables) {
		this.responsables = responsables;
	}

	public List<TipoIncidencia> getTiposIncidencia() {
		return tiposIncidencia;
	}

	public void setTiposIncidencia(List<TipoIncidencia> tiposIncidencia) {
		this.tiposIncidencia = tiposIncidencia;
	}

	public List<EstadoIncidencia> getEstadosIncidencia() {
		return estadosIncidencia;
	}

	public void setEstadosIncidencia(List<EstadoIncidencia> estadosIncidencia) {
		this.estadosIncidencia = estadosIncidencia;
	}

	public List<PrioridadIncidencia> getPrioridadIncidencia() {
		return prioridadIncidencia;
	}

	public void setPrioridadIncidencia(List<PrioridadIncidencia> prioridadIncidencia) {
		this.prioridadIncidencia = prioridadIncidencia;
	}
}
