package com.AppRH.AppRH.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AppRH.AppRH.models.Coopcadastro;
import com.AppRH.AppRH.models.Coopendereco;
import com.AppRH.AppRH.models.Cooperado;
import com.AppRH.AppRH.models.Cotaparte;
import com.AppRH.AppRH.models.Dividas;
import com.AppRH.AppRH.models.Lgpd;
import com.AppRH.AppRH.models.Telefone;
import com.AppRH.AppRH.repository.CoopcadastroRepository;
import com.AppRH.AppRH.repository.CooperadoRepository;
import com.AppRH.AppRH.repository.CotaRepository;
import com.AppRH.AppRH.repository.DividasRepository;
import com.AppRH.AppRH.repository.EnderecoRepository;
import com.AppRH.AppRH.repository.LgpdRepository;
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
	
	@Autowired
	private CoopcadastroRepository cc;
	
	@Autowired
	private LgpdRepository lr;
	
	//INSERE COOPERADO
	@RequestMapping(value = "/cadastrarCooperado")
	public String form() {
		return "cooperado/formCooperado";
	}
	
	public Integer AchaMaior(List<Cooperado> cooperados) {
		int maiorMatricula = -1;

		for (Cooperado cooperado : cooperados) {
		    int matricula = cooperado.getCoopmatricula(); // Supondo que existe um método getMatricula() na classe Cooperado
		    if (matricula > maiorMatricula) {
		        maiorMatricula = matricula;
		    }
		}

		return maiorMatricula;		
		
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
		ModelAndView mv = new ModelAndView("cooperado/listaCooperados");
		Iterable<Cooperado>cooperados= new ArrayList<Cooperado>();	
		mv.addObject("cooperados",cooperados);
		return mv;
	}
	@RequestMapping(value = "/cooperados", method = RequestMethod.POST)
	public ModelAndView buscarIndex(@RequestParam("buscar") String buscar, @RequestParam("coopnome") String coopnome) {
		
		ModelAndView mv = new ModelAndView("cooperado/listaCooperados");
		String mensagem = "Resultados da busca por " + buscar;
		
		if(coopnome.equals("")){
			if (buscar.equals("")) {				
				mv.addObject("cooperados",cr.encontrarTodos());
			} else {
				mv.addObject("cooperados",cr.findByCoopnomesCooperados(buscar));
			}
			
		}else if(coopnome.equals("all")){
			if (buscar.equals("")) {				
				mv.addObject("cooperados",cr.encontrarTodos());
			} else {
				mv.addObject("cooperados",cr.findByCoopnomesCooperados(buscar));
			}
			
		}else if(coopnome.equals("inativos")) {
			if (buscar.equals("")) {
				
				mv.addObject("cooperados",cr.encontrarInativos());
			} else {
				mv.addObject("cooperados",cr.findByCoopnomesCooperadoInativo(buscar));
			}
		}else if(coopnome.equals("ativos")) {
			if (buscar.equals("")) {
				
				mv.addObject("cooperados",cr.encontrarAtivos());
			} else {
				mv.addObject("cooperados",cr.findByCoopnomesCooperadoAtivo(buscar));
			}
		}else {
			mv.addObject("cooperados",cr.findByCoopnomesCooperados(buscar));
		}
		
		mv.addObject("mensagem", mensagem);
		
		return mv;	
	}
	
	//LISTAR COOPERADOS ATIVOS
	
	@RequestMapping("/cooperadosA")
	public ModelAndView listaCooperadosAtivos() {
		ModelAndView mv = new ModelAndView("cooperado/listaCooperado");
		Iterable<Cooperado>cooperados=cr.encontrarAtivos();
		
		mv.addObject("cooperados",cooperados);
		return mv;			
	}
	
	//LISTAR COOPERADOS INATIVOS
	
		@RequestMapping("/cooperadosN")
		public ModelAndView listaCooperadosInativos() {
			ModelAndView mv = new ModelAndView("cooperado/listaCooperado");
			Iterable<Cooperado>cooperados=cr.encontrarInativos();
			
			mv.addObject("cooperados",cooperados);
			return mv;			
		}
	
	//LISTAR ZERADA
	
		@RequestMapping("/cooperadosI")
		public ModelAndView listaCooperadosInicio() {
			ModelAndView mv = new ModelAndView("cooperado/listainicio");
			//ModelAndView mv = new ModelAndView("cooperado/listaCooperados");
			return mv;			
		}
	
	
	@RequestMapping(value="/{coopmatricula}")
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
		
		Iterable<Coopcadastro> coopcadastro = cc.findByCooperado(cooperado);
		mv.addObject("coopcadastro",coopcadastro);
		
		Iterable<Lgpd> lgpd = lr.findByCooperado(cooperado);
		mv.addObject("lgpd",lgpd);

		
		return mv;		
	}
	//Mostrar Dívidas
	@RequestMapping(value="/divida{coopmatricula}")
	public ModelAndView divida(@PathVariable("coopmatricula") int coop_matricula) {
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
		
		return mv;		
	}
	//Mostrar Cota Parte
	@RequestMapping(value="/cota{coopmatricula}")
	public ModelAndView cota(@PathVariable("coopmatricula") int coop_matricula) {
		Cooperado cooperado = cr.findByCoopmatricula(coop_matricula);
		ModelAndView mv = new ModelAndView("cooperado/cota");
		mv.addObject("cooperado",cooperado);		
		
		Iterable<Cotaparte> cotaparte = cpr.findByCooperado(cooperado);
		mv.addObject("cotaparte",cotaparte);
		
		return mv;		
	}
	
	//Mostrar Todas as Cotas Partes Não pagas
		@RequestMapping("/cotas")
		public ModelAndView cotas() {
			ModelAndView mv = new ModelAndView("cooperado/cotas");
			Iterable<Cotaparte> cotapartes = cpr.encontrarTodos();
			mv.addObject("cotapartes",cotapartes);
			
			/*double somacota=0;
			
			for (Cota cotas : cotas) {
				if(cotas.getCoopdatapagamento()==null) {
					somacota = somacota + cotas.getCoopvalor();
				}						    
			}			
			*/
			
			return mv;		
		}
		
		//Abre a página de relatórios
				@RequestMapping("/relatorios")
				public ModelAndView relatorios() {
					ModelAndView mv = new ModelAndView("cooperado/relatorios");
					return mv;		
				}
		
		
	//Mostrar Endereço
		@RequestMapping(value="/coopendereco{coopmatricula}")
		public ModelAndView coopendereco(@PathVariable("coopmatricula") int coop_matricula) {
			Cooperado cooperado = cr.findByCoopmatricula(coop_matricula);
			ModelAndView mv = new ModelAndView("cooperado/coopendereco");
			mv.addObject("cooperado",cooperado);		
			
			Iterable<Coopendereco> coopendereco = er.findByCooperado(cooperado);
			mv.addObject("coopendereco",coopendereco);
			
			return mv;		
		}
		
	//Mostrar Dados de Cadastro
			@RequestMapping(value="/coopcadastro{coopmatricula}")
		public ModelAndView coopcadastro(@PathVariable("coopmatricula") int coop_matricula) {
			Cooperado cooperado = cr.findByCoopmatricula(coop_matricula);
			ModelAndView mv = new ModelAndView("cooperado/dadoscadastrais");
			mv.addObject("cooperado",cooperado);		
			
			Iterable<Coopcadastro> coopcadastro = cc.findByCooperado(cooperado);
			mv.addObject("coopcadastro",coopcadastro);
			
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
	@RequestMapping(value="/{coopmatricula}",method=RequestMethod.POST)
	public String detalhesCooperadosPost(@PathVariable("coopmatricula") int coop_matricula, @Valid Telefone telefone,BindingResult result, RedirectAttributes attributes) {
		System.out.println("aqui"+coop_matricula);
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos");
			return "redirect:/{coopmatricula}";
		}
		
		
		//TIPO DE TELEFONE DUPLICADO
		if(tr.findBycooptelnumero(telefone.getCooptelnumero())!=null){
			attributes.addFlashAttribute("mensagem_erro","Número duplicado");
			return "redirect:/{coopmatricula}";
		}
		
		Cooperado cooperado = cr.findByCoopmatricula(coop_matricula);
		
		System.out.println("aquiget"+cooperado.getCoopmatricula());
		System.out.println("aqui2"+coop_matricula);
		System.out.println("aqui4"+telefone);
		System.out.println("aqui5"+cooperado);
		//telefone.setCoopmatricula(coop_matricula);
		
		telefone.setCooperado(cooperado);
		System.out.println("aqui3"+telefone.getCoopmatricula() );
		tr.save(telefone);
		attributes.addFlashAttribute("mensagem","Telefone adicionado com sucesso!");
		return "redirect:/{coopmatricula}";
		
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
			return "redirect:/{coop_matricula}";
		}
		//TELEFONE DUPLICADO
		if(tr.findBycooptelnumero(telefone.getCooptelnumero())!=null) {
			attributes.addFlashAttribute("mensagem erro","Tipo duplicado");
			return "redirect:/{coop_matricula}";
		}
		
		Cooperado cooperado = cr.findByCoopmatricula(coop_matricula);
		telefone.setCooperado(cooperado);
		tr.save(telefone);
		attributes.addFlashAttribute("mensagem","Telefone adicionado com sucesso!");
		return "redirect:/{coop_matricula}";
	
	}
	//METODOS QUE ATUALIZAM COOPERADO
	//FORMULÁRIO ALTERA COOPERADO
	
	@RequestMapping(value="/editar-cooperado")
	public ModelAndView editarCooperado(Integer coop_matricula) {
		Cooperado cooperado = cr.findByCoopmatricula(coop_matricula);
		ModelAndView mv = new ModelAndView("cooperado/update-cooperado");
		mv.addObject("cooperado",cooperado);
		
		/*Lgpd lgpd = lr.findByCoopmatricula(coop_matricula);
		ModelAndView mv = new ModelAndView("cooperado/update-cooperado");
		mv.addObject("lgpd",lgpd);*/
		
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
