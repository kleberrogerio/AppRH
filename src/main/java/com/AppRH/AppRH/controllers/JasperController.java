package com.AppRH.AppRH.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.AppRH.AppRH.service.JasperService;

@Controller
public class JasperController {
	
	@Autowired
	private JasperService service;
	
	@GetMapping("/relatorio/pdf/jr1")
	public void exibirRelatorio01(@RequestParam("code") String code,
			@RequestParam("acao") String acao,
			HttpServletResponse response) throws IOException {
		byte[] bytes= service.exportarPdf(code);
		response.setContentType(MediaType.APPLICATION_PDF_VALUE);
		if(acao.equals("v")) {
			response.setHeader("Content-disposition","inline; filename=relatorio-"+code+".pdf");
		}else {
			response.setHeader("Content-disposition","attachment; filename=relatorio-"+code+".pdf");			
		}
		response.getOutputStream().write(bytes);
	}
	
	@GetMapping("/relatorio/pdf/jr2")
	public void exibirRelatorio02(@RequestParam("code") String code,
			@RequestParam("acao") String acao,
			HttpServletResponse response) throws IOException {
		byte[] bytes= service.exportarPdf(code);
		response.setContentType(MediaType.APPLICATION_PDF_VALUE);
		if(acao.equals("v")) {
			response.setHeader("Content-disposition","inline; filename=relatorio-"+code+".pdf");
		}else {
			response.setHeader("Content-disposition","attachment; filename=relatorio-"+code+".pdf");			
		}
		response.getOutputStream().write(bytes);
	}
	
	@GetMapping("/relatorio/pdf/jr3")
	public void exibirRelatorio03(@RequestParam("code") String code,
			@RequestParam("acao") String acao,
			HttpServletResponse response) throws IOException {
		byte[] bytes= service.exportarPdf(code);
		response.setContentType(MediaType.APPLICATION_PDF_VALUE);
		if(acao.equals("v")) {
			response.setHeader("Content-disposition","inline; filename=relatorio-"+code+".pdf");
		}else {
			response.setHeader("Content-disposition","attachment; filename=relatorio-"+code+".pdf");			
		}
		response.getOutputStream().write(bytes);
	}
	@GetMapping("/relatorio/pdf/jr4")
	public void exibirRelatorio04(@RequestParam("code") String code,
			@RequestParam("acao") String acao,
			HttpServletResponse response) throws IOException {
		byte[] bytes= service.exportarPdf(code);
		response.setContentType(MediaType.APPLICATION_PDF_VALUE);
		if(acao.equals("v")) {
			response.setHeader("Content-disposition","inline; filename=relatorio-"+code+".pdf");
		}else {
			response.setHeader("Content-disposition","attachment; filename=relatorio-"+code+".pdf");			
		}
		response.getOutputStream().write(bytes);
	}
	@GetMapping("/relatorio/pdf/jr5")
	public void exibirRelatorio05(@RequestParam("code") String code,
			@RequestParam("acao") String acao,
			HttpServletResponse response) throws IOException {
		byte[] bytes= service.exportarPdf(code);
		response.setContentType(MediaType.APPLICATION_PDF_VALUE);
		if(acao.equals("v")) {
			response.setHeader("Content-disposition","inline; filename=relatorio-"+code+".pdf");
		}else {
			response.setHeader("Content-disposition","attachment; filename=relatorio-"+code+".pdf");			
		}
		response.getOutputStream().write(bytes);
	}

}
