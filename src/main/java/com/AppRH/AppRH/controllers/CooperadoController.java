package com.AppRH.AppRH.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AppRH.AppRH.models.Cooperado;
import com.AppRH.AppRH.models.Telefone;
import com.AppRH.AppRH.repository.CooperadoRepository;
import com.AppRH.AppRH.repository.TelefoneRepository;

@Controller
public class CooperadoController {
	
	@Autowired
	private CooperadoRepository cr;
	
	@Autowired
	private TelefoneRepository tr;
	
	//INSERE COOPERADO
	@RequestMapping(value = "/cadastrarCooperado",method = RequestMethod.GET)
	public String form() {
		return "cooperado/formCooperado";
	}
	
	@RequestMapping(value = "/cadastrarCooperado",method = RequestMethod.POST)
	public String form(@Valid Cooperado cooperado, BindingResult result, RedirectAttributes attributes) {
		
		/*if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem","Verifique os campos....");
			return "redirect:/cadastrarCooperado";
		}*/
		 cr.save(cooperado);
		 attributes.addFlashAttribute("mensagem","Cooperado cadastrado com sucesso!");
		 return "redirect:/cadastrarCooperado";
	}
	
	//LISTAR COOPERADOS
	
	@RequestMapping("/cooperados")
	public ModelAndView listaCooperados() {
		ModelAndView mv = new ModelAndView("cooperado/listaCooperado");
		Iterable<Cooperado>cooperados=cr.findAll();
		mv.addObject("cooperados",cooperados);
		return mv;
		
	}
	
	@RequestMapping(value="/{coopindexcod}",method=RequestMethod.GET)
	public ModelAndView detalhesCooperado(@PathVariable("coopindexcod") long coop_index_cod) {
		Cooperado cooperado = cr.findByCoopindexcod(coop_index_cod);
		ModelAndView mv = new ModelAndView("cooperado/detalhesCooperado");
		mv.addObject("cooperado",cooperado);
		
		Iterable<Telefone> telefones = tr.findByCooperado(cooperado);
		mv.addObject("telefones",telefones);
		
		return mv;
	}
		
	//DELETA COOPERADO
	@RequestMapping("/deletarCooperado")
	public String deletarCooperado(long coop_index_cod) {
		Cooperado cooperado = cr.findByCoopindexcod(coop_index_cod);
		cr.delete(cooperado);
		return "redirect:/cooperados";
	}
	
	public String detalhesCooperadosPost(@PathVariable("coop_index_cod") long coop_index_cod, @Valid Telefone telefone,BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos");
			return "redirect:/{coop_index_cod}";
		}
		
		
		//TIPO DE TELEFONE DUPLICADO
		if(tr.findByCoopteltipo(telefone.getCoopteltipo())!=null){
			attributes.addFlashAttribute("mensagem erro","Tipo duplicado");
			return "redirect:/{coop_index_cod}";
		}
		
		Cooperado cooperado = cr.findByCoopindexcod(coop_index_cod);
		telefone.setCooperado(cooperado);
		tr.save(telefone);
		attributes.addFlashAttribute("mensagem","Telefone adicionado com sucesso!");
		return "redirect:/{codigo}";
		
	}
	
	//DELETA TELEFONE
	@RequestMapping("/deletarTelefone")
	public String deletarTelefone(long coop_tel_index_cod) {
		Telefone telefone = tr.findByCooptelindexcod(coop_tel_index_cod);
		tr.delete(telefone);		
		return "redirect:/telefones";
		
	}
	
	public String detalhesTelefonePost(@PathVariable("coop_index_cod") long coop_index_cod, @Valid Telefone telefone,
			BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos");
			return "redirect:/{coop_index_cod}";
		}
		//TELEFONE DUPLICADO
		if(tr.findBycooptelnumero(telefone.getCooptelnumero())!=null) {
			attributes.addFlashAttribute("mensagem erro","Tipo duplicado");
			return "redirect:/{coop_index_cod}";
		}
		
		Cooperado cooperado = cr.findByCoopindexcod(coop_index_cod);
		telefone.setCooperado(cooperado);
		tr.save(telefone);
		attributes.addFlashAttribute("mensagem","Telefone adicionado com sucesso!");
		return "redirect:/{codigo}";
	
	}
	//METODOS QUE ATUALIZAM COOPERADO
	//FORMULÁRIO ALTERA COOPERADO
	
	@RequestMapping(value="/editar-cooperado",method = RequestMethod.GET)
	public ModelAndView editarCooperado(long coop_index_cod) {
		Cooperado cooperado = cr.findByCoopindexcod(coop_index_cod);
		ModelAndView mv = new ModelAndView("cooperado/update-cooperado");
		mv.addObject("cooperado",cooperado);
		return mv;		
	}
	
	//UPDATE COOPERADO
	public String updateCooperado(@Valid Cooperado cooperado,BindingResult result, RedirectAttributes attributes) {
		cr.save(cooperado);
		attributes.addFlashAttribute("sucess","Cooperado alterado com sucesso");
		long codigoLong = cooperado.getCoopindexcod();
		String coop_index_cod=""+codigoLong;
		return "redirect:/"+coop_index_cod;
	}
	
	
}
