package com.AppRH.AppRH.models;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;



import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="coop_cadastro")
public class Coopcadastro implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="coop_cad_index_cod")
	private int coopindexcod;
	
	@NotNull
	@Column(name="coop_matricula",insertable = false, updatable = false)
	private int coopmatricula;
	
	@Column(name="coop_data_cadastro")
	private Date coopdatacadastro;
	
	@Column(name="coop_data_admissao")
	private Date coopdataadmissao;
	
	@Column(name="coop_data_desligamento")
	private Date coopdatadesligamento;
	
	@Column(name="coop_motivo_desl")
	private String coopmotivodesl;
	
	@Column(name="coop_cooperado")
	private String coopcooperado;
	
	@Column(name="coop_data_admissibilidade")
	private Date coopdataadmissibilidade;
	
	@Column(name="coop_teste_psicologico")
	private String cooptestepsicologico;
	
	@Column(name="coop_teste_tecnico")
	private String cooptestetecnico;
	
	@Lob
	@Column(name="coop_informacoes")
	private Blob coopinformacoes;
	
	@Column(name="coop_restricao")
	private String cooprestricao;
	
	@Lob
	@Column(name="coop_anotacoes")
	private String coopanotacoes;
	
	@Column(name="coop_data_testepsico")
	private Date coopdataatestepsico;
	
	@Column(name="coop_data_teste_tecnico")
	private Date coopdataatestetecnico;
	
	@Column(name="coop_sel")
	private String coopsel;
	
	@Column(name="coop_indicacao")
	private Integer coopindicacao;
	
	@Column(name="coop_ocorrencias")
	private Blob coopocorrencias;
	
	@ManyToOne
	@JoinColumn(name = "coop_matricula")
	private Cooperado cooperado;

	public int getCoopindexcod() {
		return coopindexcod;
	}

	public void setCoopindexcod(int coopindexcod) {
		this.coopindexcod = coopindexcod;
	}

	public int getCoopmatricula() {
		return coopmatricula;
	}

	public void setCoopmatricula(int coopmatricula) {
		this.coopmatricula = coopmatricula;
	}

	public Date getCoopdatacadastro() {
		return coopdatacadastro;
	}

	public void setCoopdatacadastro(Date coopdatacadastro) {
		this.coopdatacadastro = coopdatacadastro;
	}

	public Date getCoopdataadmissao() {
		return coopdataadmissao;
	}

	public void setCoopdataadmissao(Date coopdataadmissao) {
		this.coopdataadmissao = coopdataadmissao;
	}

	public Date getCoopdatadesligamento() {
		return coopdatadesligamento;
	}

	public void setCoopdatadesligamento(Date coopdatadesligamento) {
		this.coopdatadesligamento = coopdatadesligamento;
	}

	public String getCoopmotivodesl() {
		return coopmotivodesl;
	}

	public void setCoopmotivodesl(String coopmotivodesl) {
		this.coopmotivodesl = coopmotivodesl;
	}

	public String getCoopcooperado() {
		return coopcooperado;
	}

	public void setCoopcooperado(String coopcooperado) {
		this.coopcooperado = coopcooperado;
	}

	public Date getCoopdataadmissibilidade() {
		return coopdataadmissibilidade;
	}

	public void setCoopdataadmissibilidade(Date coopdataadmissibilidade) {
		this.coopdataadmissibilidade = coopdataadmissibilidade;
	}

	public String getCooptestepsicologico() {
		return cooptestepsicologico;
	}

	public void setCooptestepsicologico(String cooptestepsicologico) {
		this.cooptestepsicologico = cooptestepsicologico;
	}

	public String getCooptestetecnico() {
		return cooptestetecnico;
	}

	public void setCooptestetecnico(String cooptestetecnico) {
		this.cooptestetecnico = cooptestetecnico;
	}

	public Blob getCoopinformacoes() {
		return coopinformacoes;
	}

	public void setCoopinformacoes(Blob coopinformacoes) {
		this.coopinformacoes = coopinformacoes;
	}

	public String getCooprestricao() {
		return cooprestricao;
	}

	public void setCooprestricao(String cooprestricao) {
		this.cooprestricao = cooprestricao;
	}

	public String getCoopanotacoes() {
		return coopanotacoes;
	}

	public void setCoopanotacoes(String coopanotacoes) {
		this.coopanotacoes = coopanotacoes;
	}

	public Date getCoopdataatestepsico() {
		return coopdataatestepsico;
	}

	public void setCoopdataatestepsico(Date coopdataatestepsico) {
		this.coopdataatestepsico = coopdataatestepsico;
	}

	public Date getCoopdataatestetecnico() {
		return coopdataatestetecnico;
	}

	public void setCoopdataatestetecnico(Date coopdataatestetecnico) {
		this.coopdataatestetecnico = coopdataatestetecnico;
	}

	public String getCoopsel() {
		return coopsel;
	}

	public void setCoopsel(String coopsel) {
		this.coopsel = coopsel;
	}

	public Integer getCoopindicacao() {
		return coopindicacao;
	}

	public void setCoopindicacao(Integer coopindicacao) {
		this.coopindicacao = coopindicacao;
	}

	public Blob getCoopocorrencias() {
		return coopocorrencias;
	}

	public void setCoopocorrencias(Blob coopocorrencias) {
		this.coopocorrencias = coopocorrencias;
	}

	public Cooperado getCooperado() {
		return cooperado;
	}

	public void setCooperado(Cooperado cooperado) {
		this.cooperado = cooperado;
	}
	
	
}
