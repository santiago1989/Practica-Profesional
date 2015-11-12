package com.gestor.backend.service;

import java.io.FileNotFoundException;
import java.util.Collection;

import net.sf.jasperreports.engine.JRException;

import com.gestor.backend.dto.ReportResult;

public interface ReportService {
	
	ReportResult writeReport(String reportType,String templatePath,Collection<?> dataSource) throws FileNotFoundException, JRException;	
}
