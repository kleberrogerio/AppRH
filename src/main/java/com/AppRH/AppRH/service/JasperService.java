package com.AppRH.AppRH.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class JasperService {
	
	private static final String JASPER_DIRETORIO="classpath:jasper/";
	private static final String JASPER_PREFIXO="Cooperados-";
	private static final String JASPER_SUFIXO=".jasper";

	
	@Autowired
	private Connection connection;

	private Map<String,Object> params = new HashMap<>();
	
	public void addParams(String key, Object value) {
		this.params.put(key, value);
	}
	
	public byte[]exportarPdf(String code){
		byte[] bytes = null;
		try {
			File file = ResourceUtils.getFile(JASPER_DIRETORIO.concat(JASPER_PREFIXO).concat(code).concat(JASPER_SUFIXO));
			JasperPrint print = JasperFillManager.fillReport(file.getAbsolutePath(), params,connection);
			bytes = JasperExportManager.exportReportToPdf(print);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bytes;
	}
}
/*package com.AppRH.AppRH;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class JasperService {
	
	private Map<String,Object> params = new LinkedHashMap<>();
	
	public void addParams(String key, Object value) {
		this.params.put(key, value);
	}
	
	
	public void exportarParaPDF(String jrxml, Connection connection,String saida) {
		JasperReport report = compilarJrxml(jrxml);
		try {
			OutputStream out = new FileOutputStream(saida);
			JasperPrint print = JasperFillManager.fillReport(report, this.params,connection);
			JasperExportManager.exportReportToPdfStream(print, out);
		} catch (JRException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void abrirPontoJasper(String jasperFile, Connection connection) {
		try {
			InputStream is = getClass().getClassLoader().getResourceAsStream(jasperFile);
			JasperPrint print = JasperFillManager.fillReport(is, this.params,connection);
			JasperViewer viewer = new JasperViewer(print);
			viewer.setVisible(true);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
	
	
	public void abrirJasperViewer(String jrxml, Connection connection) {
		JasperReport report = compilarJrxml(jrxml);
		try {
			JasperPrint print = JasperFillManager.fillReport(report, this.params,connection);
			JasperViewer viewer = new JasperViewer(print);
			viewer.setVisible(true);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
	
	private JasperReport compilarJrxml(String arquivo) {		
		try {
			InputStream is = getClass().getClassLoader().getResourceAsStream(arquivo);
			return JasperCompileManager.compileReport(is);
		} catch (JRException e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	
}*/
