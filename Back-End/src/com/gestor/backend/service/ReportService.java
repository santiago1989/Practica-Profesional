package com.gestor.backend.service;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collection;

import net.sf.jasperreports.engine.JRException;

public interface ReportService {
	
	InputStream writeReport(String templatePath,Collection<?> dataSource) throws FileNotFoundException, JRException;	
}
