package com.AppRH.AppRH.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="coop_lgpd")
public class Lgpd implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="coop_lgpd_index_cod")
	private int cooplgpdindexcod;
	
	@NotNull
	@Column(name="coop_matricula",insertable = false, updatable = false)
	private int coopmatricula;
	
	@Column(name="coop_ligacao")
	private String coopligacao;
	
	@Column(name="coop_whatsapp")
	private String coopwhatsapp;
	
	@Column(name="coop_correio")
	private String coopcorreio;
	
	@Column(name="coop_nenhum")
	private String coopnenhum;
	
	@ManyToOne
	@JoinColumn(name = "coop_matricula")
	private Cooperado cooperado;

}

