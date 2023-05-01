package com.AppRH.AppRH.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import com.AppRH.AppRH.models.Cooperado;
import com.AppRH.AppRH.models.Telefone;
import com.AppRH.AppRH.models.Funcionario;
import com.AppRH.AppRH.models.Dependentes;

import com.AppRH.AppRH.repository.CooperadoRepository;
import com.AppRH.AppRH.repository.TelefoneRepository;


@Controller
public class BuscaController {
	
	@Autowired
	private CooperadoRepository cr;
	
	@Autowired
	private TelefoneRepository tr;
	
	
}