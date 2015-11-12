package com.gestor.backend.service.impl;

import java.io.ByteArrayOutputStream;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import com.gestor.backend.service.ExportService;
import com.gestor.common.enums.ContentType;

public class PDFExportService implements ExportService {

	@Override
	public ByteArrayOutputStream export(JasperPrint print) throws JRException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterInput(new SimpleExporterInput(print));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
		exporter.exportReport();
		return output;
	}
	@Override
	public ContentType getContentType() {
		return ContentType.PDF;
	}

}
