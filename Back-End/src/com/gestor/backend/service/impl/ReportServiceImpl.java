package com.gestor.backend.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import com.gestor.backend.service.ReportService;

public class ReportServiceImpl implements ReportService {

	@Override
	public InputStream writeReport(String templatePath, Collection<?> dataSource) throws FileNotFoundException, JRException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		File file = new File(templatePath);
		FileInputStream template = new FileInputStream(file);
		JasperDesign design = JRXmlLoader.load(template);
		JasperReport report = JasperCompileManager.compileReport(design);
		JasperPrint print = JasperFillManager.fillReport(report, new HashMap<String, Object>(), new JRBeanCollectionDataSource(dataSource));
		JRXlsExporter exporter = new JRXlsExporter();
		exporter.setExporterInput(new SimpleExporterInput(print));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
		exporter.exportReport();
		return new ByteArrayInputStream(output.toByteArray());
	}

}
