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

import com.AppRH.AppRH.models.Coopendereco;
import com.AppRH.AppRH.models.Cooperado;
import com.AppRH.AppRH.models.Cotaparte;
import com.AppRH.AppRH.models.Dividas;
import com.AppRH.AppRH.models.Telefone;
import com.AppRH.AppRH.repository.CooperadoRepository;
import com.AppRH.AppRH.repository.CotaRepository;
import com.AppRH.AppRH.repository.DividasRepository;
import com.AppRH.AppRH.repository.EnderecoRepository;
import com.AppRH.AppRH.repository.TelefoneRepository;

@Controller
public class CooperadoController {
	
	@Autowired
	private CooperadoRepository cr;
	
	@Autowired
	private TelefoneRepository tr;
	
	@Autowired
	private DividasRepository dr;
	
	@Autowired
	private CotaRepository cpr;
	
	@Autowired
	private EnderecoRepository er;
	
	//INSERE COOPERADO
	@RequestMapping(value = "/cadastrarCooperado",method = RequestMethod.GET)
	public String form() {
		return "cooperado/formCooperado";
	}
	
	@RequestMapping(value = "/cadastrarCooperado",method = RequestMethod.POST)
	public String form(@Valid Cooperado cooperado, BindingResult result, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem","Verifique os campos....");
			return "redirect:/cadastrarCooperado";
		}
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
	
	@RequestMapping(value="/{coopmatricula}",method=RequestMethod.GET)
	public ModelAndView detalhesCooperado(@PathVariable("coopmatricula") int coop_matricula) {
		Cooperado cooperado = cr.findByCoopmatricula(coop_matricula);
		ModelAndView mv = new ModelAndView("cooperado/detalhesCooperado");
		mv.addObject("cooperado",cooperado);
		
		Iterable<Telefone> telefones = tr.findByCooperado(cooperado);
		mv.addObject("telefones",telefones);
		
		Iterable<Dividas> dividas = dr.findByCooperado(cooperado);
		mv.addObject("dividas",dividas);
		
		Iterable<Cotaparte> cotaparte = cpr.findByCooperado(cooperado);
		mv.addObject("cotaparte",cotaparte);
		
		return mv;		
	}
	//Mostrar Dívidas
	@RequestMapping(value="/divida{coopmatricula}",method=RequestMethod.GET)
	public ModelAndView divida(@PathVariable("coopmatricula") int coop_matricula) {
		//public ModelAndView divida(@PathVariable("coopmatricula") int coop_matricula,@RequestParam("buscar") String buscar, @RequestParam("coopdatapagamento") Date coopdatapagamento) {
		Cooperado cooperado = cr.findByCoopmatricula(coop_matricula);
		ModelAndView mv = new ModelAndView("cooperado/divida");
		mv.addObject("cooperado",cooperado);		
		
		Iterable<Dividas> dividas = dr.findByCooperado(cooperado);
		
		double soma=0;
		
		for (Dividas divida : dividas) {
			if(divida.getCoopdatapagamento()==null) {
				soma = soma + divida.getCoopvalor();
			}
					    
		}
		
		mv.addObject("dividas",dividas);
        mv.addObject("soma",soma);
		
		//mv.addObject("dividas",dr.findByCoopdividasCooperado(buscar));
		
		return mv;		
	}
	//Mostrar Cota Parte
	@RequestMapping(value="/cota{coopmatricula}",method=RequestMethod.GET)
	public ModelAndView cota(@PathVariable("coopmatricula") int coop_matricula) {
		Cooperado cooperado = cr.findByCoopmatricula(coop_matricula);
		ModelAndView mv = new ModelAndView("cooperado/cota");
		mv.addObject("cooperado",cooperado);		
		
		Iterable<Cotaparte> cotaparte = cpr.findByCooperado(cooperado);
		mv.addObject("cotaparte",cotaparte);
		
		return mv;		
	}
	
	//Mostrar Endereço
		@RequestMapping(value="/coopendereco{coopmatricula}",method=RequestMethod.GET)
		public ModelAndView coopendereco(@PathVariable("coopmatricula") int coop_matricula) {
			Cooperado cooperado = cr.findByCoopmatricula(coop_matricula);
			ModelAndView mv = new ModelAndView("cooperado/coopendereco");
			mv.addObject("cooperado",cooperado);		
			
			Iterable<Coopendereco> coopendereco = er.findByCooperado(cooperado);
			mv.addObject("coopendereco",coopendereco);
			
			return mv;		
		}
		
	//DELETA COOPERADO
	@RequestMapping("/deletarCooperado")
	public String deletarCooperado(int coopmatricula) {
		Cooperado cooperado = cr.findByCoopmatricula(coopmatricula);
		cr.delete(cooperado);
		return "redirect:/cooperados";
	}
	
	//ADICIONAR TELEFONE
	@RequestMapping(value="/cooperado/{coopmatricula}",method=RequestMethod.POST)
	public String detalhesCooperadosPost(@PathVariable("coopmatricula") int coop_matricula, @Valid Telefone telefone,BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos");
			return "redirect:/cooperado/{coopmatricula}";
		}
		
		
		//TIPO DE TELEFONE DUPLICADO
		if(tr.findBycooptelnumero(telefone.getCooptelnumero())!=null){
			attributes.addFlashAttribute("mensagem_erro","Número duplicado");
			return "redirect:/cooperado/{coopmatricula}";
		}
		
		Cooperado cooperado = cr.findByCoopmatricula(coop_matricula);
		telefone.setCooperado(cooperado);
		tr.save(telefone);
		attributes.addFlashAttribute("mensagem","Telefone adicionado com sucesso!");
		return "redirect:/cooperado/{coopmatricula}";
		
	}
	
	//DELETA TELEFONE
	@RequestMapping("/deletarTelefone")
	public String deletarTelefone(int cooptelindexcod) {
		Telefone telefone = tr.findByCooptelindexcod(cooptelindexcod);
		Cooperado cooperado = telefone.getCooperado();
		String coop_matricula = "" + cooperado.getCoopmatricula();
		tr.delete(telefone);	
		
		return "redirect:/" +coop_matricula;
		
	}
	
	public String detalhesTelefonePost(@PathVariable("coopmatricula") int coop_matricula, @Valid Telefone telefone,
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
		
		Cooperado cooperado = cr.findByCoopmatricula(coop_matricula);
		telefone.setCooperado(cooperado);
		tr.save(telefone);
		attributes.addFlashAttribute("mensagem","Telefone adicionado com sucesso!");
		return "redirect:/{coop_matricula}";
	
	}
	//METODOS QUE ATUALIZAM COOPERADO
	//FORMULÁRIO ALTERA COOPERADO
	
	@RequestMapping(value="/editar-cooperado",method = RequestMethod.GET)
	public ModelAndView editarCooperado(int coop_index_cod) {
		Cooperado cooperado = cr.findByCoopindexcod(coop_index_cod);
		ModelAndView mv = new ModelAndView("cooperado/update-cooperado");
		mv.addObject("cooperado",cooperado);
		return mv;		
	}
	
	//UPDATE COOPERADO
	@RequestMapping(value="/editar-cooperado",method = RequestMethod.POST)
	public String updateCooperado(@Valid Cooperado cooperado,BindingResult result, RedirectAttributes attributes) {
		cr.save(cooperado);
		attributes.addFlashAttribute("sucess","Cooperado alterado com sucesso");
		long codigoInt = cooperado.getCoopmatricula();
		String coopmatricula=""+codigoInt;
		return "redirect:/" +coopmatricula;
	}
	
	
	
}
