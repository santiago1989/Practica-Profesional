package com.gestor.entidades;

import com.gestor.common.util.FileUtils;

public class Adjunto {
	
	private int id;
	
	private String url;
	
	private Incidencia incidencia;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Incidencia getIncidencia() {
		return incidencia;
	}

	public void setIncidencia(Incidencia incidencia) {
		this.incidencia = incidencia;
	}
	
	public String getFileName(){
		return FileUtils.getFileName(this.url);
	}

	public String getFileExtension(){
		return FileUtils.getFileExtension(this.url);
	}
}
