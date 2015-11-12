package com.gestor.backend.service.impl;

import java.io.ByteArrayOutputStream;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import com.gestor.backend.service.ExportService;
import com.gestor.common.enums.ContentType;

public class ExcelExportService implements ExportService {

	@Override
	public ByteArrayOutputStream export(JasperPrint print) throws JRException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		JRXlsExporter exporter = new JRXlsExporter();
		exporter.setExporterInput(new SimpleExporterInput(print));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
		exporter.exportReport();
		return output;
	}

	@Override
	public ContentType getContentType() {
		return ContentType.XLS;
	}

}
