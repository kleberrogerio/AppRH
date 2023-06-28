package com.AppRH.AppRH.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AppRH.AppRH.models.Coopcadastro;
import com.AppRH.AppRH.models.Coopendereco;
import com.AppRH.AppRH.models.Cooperado;
import com.AppRH.AppRH.models.Cotaparte;
import com.AppRH.AppRH.models.Dividas;
import com.AppRH.AppRH.models.Lgpd;
import com.AppRH.AppRH.models.LogAlteracao;
import com.AppRH.AppRH.models.Telefone;
import com.AppRH.AppRH.repository.CoopcadastroRepository;
import com.AppRH.AppRH.repository.CooperadoRepository;
import com.AppRH.AppRH.repository.CotaRepository;
import com.AppRH.AppRH.repository.DividasRepository;
import com.AppRH.AppRH.repository.EnderecoRepository;
import com.AppRH.AppRH.repository.LgpdRepository;
import com.AppRH.AppRH.repository.LogAlteracaoRepository;
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
	
	@Autowired
    private LogAlteracaoRepository la;
	
	//INSERE COOPERADO
	@PreAuthorize("hasRole('ROLE_ADMIN')")
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
		Integer maior =cr.achaMaior();
		System.out.println("MAIOR"+maior);
		
		//SALVANDO UM LOG DE INCLUSÃO
		  LogAlteracao lalte= new LogAlteracao();
		  lalte.setData(LocalDateTime.now());
		  lalte.setTabela("Cooperado");
		  lalte.setOperacao("Inclusão");
		  lalte.setDetalhes("Cooperado Inserido");
		  lalte.setCoopmatricula(maior+1);

	      la.save(lalte);
	      cooperado.setCoopindexcod(maior+1);


		
		 cr.save(cooperado);
		 attributes.addFlashAttribute("mensagem","Cooperado cadastrado com sucesso!");
		 return "redirect:/cadastrarCooperado";
	}
	
	
	//CONTROLANDO O LGPD
	@RequestMapping(value = "/ControlaLGPD/{coopmatricula}",method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void controlaLGPD(@PathVariable("coopmatricula") int coop_matricula, @RequestParam("value") String value, @RequestParam("type") String type) {
		
		Lgpd lgpd = lr.findByCoopmatricula(coop_matricula);
		
		String valor = value.equals("true") ? "SIM" : "NÃO";
		
		System.out.println(valor);
		
		if (type.equals("ligacao")) {
			lgpd.setCoopligacao(valor);
		}
		
		if (type.equals("whatsapp")) {
			lgpd.setCoopwhatsapp(valor);
		}
		
		if (type.equals("correio")) {
			lgpd.setCoopcorreio(valor);
		}
		
		lr.save(lgpd);
		
		LogAlteracao log = new LogAlteracao();
		log.setCoopmatricula(coop_matricula);
		log.setData(LocalDateTime.now(ZoneId.of("Brazil/East")));
		log.setDetalhes("Alterou o valor: " + type + " para " + valor);
		log.setTabela("coop_lgpd");
		log.setOperacao("Update");
		
		la.save(log);		
		
			}
	//LISTAR COOPERADOS
	
	@RequestMapping("/cooperados")
	//@PreAuthorize("isAuthenticated()")
	@PreAuthorize("hasAnyRole('ADMIN', 'USUARIO')")
	public ModelAndView listaCooperados() {
		ModelAndView mv = new ModelAndView("cooperado/listaCooperados");
		Iterable<Cooperado>cooperados= new ArrayList<Cooperado>();	
		mv.addObject("cooperados",cooperados);
		return mv;
	}
	
	//MÉTODO UTILIZADO PARA A BUSCA
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
	
	//VER DETALHES DOS COOPERADOS
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
	//MOSTRAR DÍVIDAS
	@PreAuthorize("hasRole('ADMIN')")
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
	//MOSTRAR COTA PARTE
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value="/cota{coopmatricula}")
	public ModelAndView cota(@PathVariable("coopmatricula") int coop_matricula) {
		Cooperado cooperado = cr.findByCoopmatricula(coop_matricula);
		ModelAndView mv = new ModelAndView("cooperado/cota");
		mv.addObject("cooperado",cooperado);		
		
		Iterable<Cotaparte> cotaparte = cpr.findByCooperado(cooperado);
		mv.addObject("cotaparte",cotaparte);
		
		return mv;		
	}
	
	//MOSTRAR TODAS AS COTAS PARTES NÃO DEVOLVIDAS AOS COOPERADOS	
		@RequestMapping("/cotas")
		@PreAuthorize("hasRole('ADMIN')")
		public ModelAndView cotas() {
			ModelAndView mv = new ModelAndView("cooperado/cotas");
			Iterable<Cotaparte> cotapartes = cpr.encontrarTodos();
			mv.addObject("cotapartes",cotapartes);
			
			return mv;		
		}
		
		//ABRE PÁGINA DE RELATÓRIOS
		//@PreAuthorize("isAuthenticated")
		@PreAuthorize("hasRole('ADMIN')")
		@RequestMapping("/relatorios")
		public ModelAndView relatorios() {
			ModelAndView mv = new ModelAndView("cooperado/relatorios");
			return mv;		
		}
		
		
	//MOSTRA OS ENDEREÇOS
		@RequestMapping(value="/coopendereco{coopmatricula}")
		@PreAuthorize("hasAnyRole('ADMIN', 'USUARIO')")
		//@PreAuthorize("isAuthenticated")
		public ModelAndView coopendereco(@PathVariable("coopmatricula") int coop_matricula) {
			Cooperado cooperado = cr.findByCoopmatricula(coop_matricula);
			ModelAndView mv = new ModelAndView("cooperado/coopendereco");
			mv.addObject("cooperado",cooperado);		
			
			Iterable<Coopendereco> coopendereco = er.findByCooperado(cooperado);
			mv.addObject("coopendereco",coopendereco);
			
			return mv;		
		}
		
	//MOSTRA OS DADOS DO CADASTRO
			@RequestMapping(value="/coopcadastro{coopmatricula}")
			//@PreAuthorize("isAuthenticated")
			@PreAuthorize("hasAnyRole('ADMIN', 'USUARIO')")
			public ModelAndView coopcadastro(@PathVariable("coopmatricula") int coop_matricula) {
			Cooperado cooperado = cr.findByCoopmatricula(coop_matricula);
			ModelAndView mv = new ModelAndView("cooperado/dadoscadastrais");
			mv.addObject("cooperado",cooperado);		
			
			Iterable<Coopcadastro> coopcadastro = cc.findByCooperado(cooperado);
			mv.addObject("coopcadastro",coopcadastro);
			
			return mv;		
		}
		
	//DELETA COOPERADO
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping("/deletarCooperado")
	public String deletarCooperado(int coopmatricula) {
		Cooperado cooperado = cr.findByCoopmatricula(coopmatricula);
		cr.delete(cooperado);
		return "redirect:/cooperados";
	}
	
	//ADICIONAR TELEFONE
	@PreAuthorize("hasRole('ADMIN')")
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
			
		telefone.setCooperado(cooperado);
		System.out.println("aqui3"+telefone.getCoopmatricula() );
		tr.save(telefone);
		attributes.addFlashAttribute("mensagem","Telefone adicionado com sucesso!");
		return "redirect:/{coopmatricula}";
		
	}
	
	//DELETA TELEFONE
	@PreAuthorize("hasRole('ADMIN')")
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
	@PreAuthorize("hasRole('ADMIN')")
	public ModelAndView editarCooperado(Integer coop_matricula) {
		Cooperado cooperado = cr.findByCoopmatricula(coop_matricula);
		ModelAndView mv = new ModelAndView("cooperado/update-cooperado");
		mv.addObject("cooperado",cooperado);
		
		return mv;	
			
	}
	
	//UPDATE COOPERADO
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value="/editar-cooperado",method = RequestMethod.POST)
	public String updateCooperado(@Valid Cooperado cooperado,BindingResult result, RedirectAttributes attributes) {
		cr.save(cooperado);
		attributes.addFlashAttribute("sucess","Cooperado alterado com sucesso");
		long codigoInt = cooperado.getCoopmatricula();
		String coopmatricula=""+codigoInt;
		return "redirect:/" +coopmatricula;
	}
	
	//MÉTODO PARA TRABALHAR COM ENCRIPTAÇÃO
	@Autowired
	private PasswordEncoder passwordEncorderBean() {
		return new BCryptPasswordEncoder();
	}
		
}
