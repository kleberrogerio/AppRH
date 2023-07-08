package com.AppRH.AppRH.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AppRH.AppRH.models.Dependentes;
import com.AppRH.AppRH.models.Funcionario;
import com.AppRH.AppRH.repository.DependenteRepository;
import com.AppRH.AppRH.repository.FuncionarioRepository;

@Controller
public class FuncionarioController {

	@Autowired
	private FuncionarioRepository fr;

	@Autowired
	private DependenteRepository dr;

	// GET que chama o form para cadastrar funcionários
	@PreAuthorize("hasAnyRole('ADMIN', 'DEVELOPER')")
	@GetMapping("/cadastrarFuncionario")
	public String form() {
		return "funcionario/formFuncionario";
	}

	// POST que cadastra funcionários
	@PreAuthorize("hasAnyRole('ADMIN', 'DEVELOPER')")
	@PostMapping(value = "/cadastrarFuncionario")
	public String form(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos");
			return "redirect:/cadastrarFuncionario";
		}

		fr.save(funcionario);
		attributes.addFlashAttribute("mensagem", "Funcionário cadastrado com sucesso!");
		return "redirect:/cadastrarFuncionario";
	}

	// GET que lista funcionários
	@PreAuthorize("hasAnyRole('ADMIN', 'DEVELOPER', 'USUARIO')")
	@GetMapping("/funcionarios")
	public ModelAndView listaFuncionarios() {
		ModelAndView mv = new ModelAndView("funcionario/listaFuncionario");
		Iterable<Funcionario> funcionarios = fr.findAll();
		mv.addObject("funcionarios", funcionarios);
		return mv;
	}

	// GET que lista dependentes e detalhes dos funcionário
	@PreAuthorize("hasAnyRole('ADMIN', 'DEVELOPER', 'USUARIO')")
	@GetMapping("/detalhes-funcionario/{id}")
	public ModelAndView detalhesFuncionario(@PathVariable("funcmatricula") int funcmatricula) {
		Funcionario funcionario = fr.findByFuncmatricula(funcmatricula);
		ModelAndView mv = new ModelAndView("funcionario/detalhes-funcionario");
		mv.addObject("funcionarios", funcionario);

		// lista de dependentes baseada no id do funcionário
		Iterable<Dependentes> dependentes = dr.findByFuncionario(funcionario);
		mv.addObject("dependentes", dependentes);

		return mv;

	}

	// POST que adiciona dependentes
	@PreAuthorize("hasAnyRole('ADMIN', 'DEVELOPER')")
	@PostMapping(value="/detalhes-funcionario/{id}")
	public String detalhesFuncionarioPost(@PathVariable("id") long id, Dependentes dependentes, BindingResult result,
			RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/detalhes-funcionario/{id}";
		}
		
		if(dr.findByFuncdepcpf(dependentes.getFuncdepcpf()) != null) {
			attributes.addFlashAttribute("mensagem_erro", "CPF duplicado");
			return "redirect:/detalhes-funcionario/{id}";
		}
		
		Funcionario funcionario = fr.findById(id);
		dependentes.setFuncionario(funcionario);
		dr.save(dependentes);
		attributes.addFlashAttribute("mensagem", "Dependente adicionado com sucesso");
		return "redirect:/detalhes-funcionario/{id}";
		
	}
	
	//GET que deleta funcionário
	@PreAuthorize("hasAnyRole('DEVELOPER')")
	@GetMapping("/deletarFuncionario")
	public String deletarFuncionario(int funcmatricula) {
		Funcionario funcionario = fr.findById(funcmatricula);
		fr.delete(funcionario);
		return "redirect:/funcionarios";
		
	}
	
	// Métodos que atualizam funcionário
	// GET que chama o FORM de edição do funcionário
	@PreAuthorize("hasAnyRole('ADMIN', 'DEVELOPER')")
	@GetMapping("/editar-funcionario")
	public ModelAndView editarFuncionario(int funcmatricula) {
		Funcionario funcionario = fr.findByFuncmatricula(funcmatricula);
		ModelAndView mv = new ModelAndView("funcionario/update-funcionario");
		mv.addObject("funcionario", funcionario);
		return mv;
	}
	
	// POST que atualiza o funcionário
	@PreAuthorize("hasAnyRole('ADMIN', 'DEVELOPER')")
	@PostMapping(value = "/editar-funcionario")
	public String updateFuncionario(@Valid Funcionario funcionario,  BindingResult result, RedirectAttributes attributes){
		
		fr.save(funcionario);
		attributes.addFlashAttribute("successs", "Funcionário alterado com sucesso!");
		
		long idLong = funcionario.getFuncid();
		String id = "" + idLong;
		return "redirect:/detalhes-funcionario/" + id;
		
	}
	
	// GET que deleta dependente
	@PreAuthorize("hasAnyRole('ADMIN', 'DEVELOPER')")
	@GetMapping("/deletarDependente")
	public String deletarDependente(String funcdepcpf) {
		Dependentes dependente = dr.findByFuncdepcpf(funcdepcpf);
		
		Funcionario funcionario = dependente.getFuncionario();
		String codigo = "" + funcionario.getFuncid();
		
		dr.delete(dependente);
		return "redirect:/detalhes-funcionario/" + codigo;
	
	}
}