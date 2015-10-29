package com.gestor.web.dto;

import java.util.List;

import com.gestor.backend.dto.CriteriaUsuario;
import com.gestor.backend.service.Service;
import com.gestor.common.enums.RolType;
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
	
	private static CriteriaUsuario criteriaAdministrativo = new CriteriaUsuario(RolType.ADMINISTRATIVO.getCode());
	
	private static CriteriaUsuario criteriaResponsable = new CriteriaUsuario(RolType.RESPONSABLE.getCode());
	
	public IncidenciaCollectionsBean(Service service) {
		super(service);
		this.owners = service.buscar(Usuario.class,criteriaAdministrativo.getCriteria());
		this.responsables = service.buscar(Usuario.class,criteriaResponsable.getCriteria());
		this.tiposIncidencia = service.findAll(TipoIncidencia.class);
		this.estadosIncidencia = service.findAll(EstadoIncidencia.class);
		this.prioridadIncidencia = service.findAll(PrioridadIncidencia.class);
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

	public List<Usuario> getOwners() {
		return owners;
	}

	public void setOwners(List<Usuario> owners) {
		this.owners = owners;
	}

	@Override
	public void refreshCollections() {
		this.owners = service.buscar(Usuario.class,criteriaAdministrativo.getCriteria());
		this.responsables = service.buscar(Usuario.class,criteriaResponsable.getCriteria());
		this.tiposIncidencia = service.findAll(TipoIncidencia.class);
	}
}
