package com.AppRH.AppRH.controllers;

import java.util.ArrayList;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.AppRH.AppRH.models.Interessado;

public class InteressadoController {
	
	//LISTAR COOPERADOS
		@GetMapping("/interessados")
		@PreAuthorize("hasAnyRole('ADMIN', 'USUARIO','DEVELOPER')")
		public ModelAndView listaInteressados() {
			ModelAndView mv = new ModelAndView("interessado/listaInteressados");
			Iterable<Interessado>interessados= new ArrayList<Interessado>();	
			mv.addObject("interessados",interessados);
			return mv;
		}

}
