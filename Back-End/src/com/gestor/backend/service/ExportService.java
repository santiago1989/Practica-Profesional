package com.gestor.backend.service;

import java.io.ByteArrayOutputStream;

import com.gestor.common.enums.ContentType;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;


public interface ExportService {
	
	ByteArrayOutputStream export(JasperPrint print) throws JRException;
	ContentType getContentType();
	
}
