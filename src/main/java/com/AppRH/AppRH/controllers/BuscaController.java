package com.AppRH.AppRH.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


//import com.AppRH.AppRH.models.Cooperado;
//import com.AppRH.AppRH.models.Telefone;
//import com.AppRH.AppRH.models.Funcionario;
//import com.AppRH.AppRH.models.Dependentes;

import com.AppRH.AppRH.repository.CooperadoRepository;
//import com.AppRH.AppRH.repository.TelefoneRepository;


@Controller
public class BuscaController {
	
	@Autowired
	private CooperadoRepository cr;
	
//	@Autowired
	//private TelefoneRepository tr;
	
	//Get
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView abrirIndex() {
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}
	
	//Post
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView buscarIndex(@RequestParam("buscar") String buscar, @RequestParam("coopnome") String coopnome) {
		
		ModelAndView mv = new ModelAndView("index");
		String mensagem = "Resultados da busca por " + buscar;
		
		if(coopnome.equals("nomecooperado")){
			mv.addObject("cooperados",cr.findByCoopnomesCooperado(buscar));
		}else if(coopnome.equals("nomedependente")) {
			mv.addObject("cooperados",cr.findByCoopnomesCooperado(buscar));
		}else if(coopnome.equals("nomefuncionario")) {
			mv.addObject("cooperados",cr.findByCoopnomesCooperado(buscar));
		}else if(coopnome.equals("nometelefone")) {
			mv.addObject("cooperados",cr.findByCoopnomesCooperado(buscar));
		}else {
			mv.addObject("cooperados",cr.findByCoopnomesCooperado(buscar));
		}
		
		mv.addObject("mensagem", mensagem);
		
		return mv;	
	}
}