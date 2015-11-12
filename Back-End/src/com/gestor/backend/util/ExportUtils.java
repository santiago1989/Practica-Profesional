package com.gestor.backend.util;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import com.gestor.backend.service.ExportService;
import com.gestor.backend.service.impl.ExcelExportService;
import com.gestor.backend.service.impl.PDFExportService;
import com.gestor.common.enums.ExportType;

public class ExportUtils{

	public static ExportService selectExport(String type,JasperPrint print) throws JRException{
		ExportType exportType = ExportType.lookUp(type);
		switch (exportType) {
		case EXCEL: 
		return new ExcelExportService();	
		case PDF: 
		return new PDFExportService();
		default:
		return null;
		}
	}
}
