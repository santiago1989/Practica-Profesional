package com.gestor.common.enums;

import org.apache.commons.lang.StringUtils;

import com.gestor.common.util.Utils;

public enum ExportType {
	
	EXCEL("excel",ContentType.XLS.getDescription()),
	PDF("pdf",ContentType.PDF.getDescription());
	
	private String nombre;

	private String extension;
	
	private ExportType(String nombre,String extension){
		this.nombre = nombre;
		this.extension = extension;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	public static ExportType lookUp(String exportStr){
		for (ExportType type : ExportType.values()) {
			if(StringUtils.containsIgnoreCase(exportStr,type.nombre)){
				return type;
			}
		}
		return null;
	}
}
