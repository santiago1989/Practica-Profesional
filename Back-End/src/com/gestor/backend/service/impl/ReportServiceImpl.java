package com.gestor.backend.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashMap;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import com.gestor.backend.dto.ReportResult;
import com.gestor.backend.service.ExportService;
import com.gestor.backend.service.ReportService;
import com.gestor.backend.util.ExportUtils;

public class ReportServiceImpl implements ReportService {

	@Override
	public ReportResult writeReport(String reportType,String templatePath, Collection<?> dataSource) throws FileNotFoundException, JRException {
		File file = new File(templatePath);
		FileInputStream template = new FileInputStream(file);
		JasperDesign design = JRXmlLoader.load(template);
		JasperReport report = JasperCompileManager.compileReport(design);
		JasperPrint print = JasperFillManager.fillReport(report, new HashMap<String, Object>(), new JRBeanCollectionDataSource(dataSource));
		ExportService service = ExportUtils.selectExport(reportType, print);
		ReportResult result = new ReportResult( new ByteArrayInputStream(service.export(print).toByteArray()),service.getContentType());
		return result;
	}

}
