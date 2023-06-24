package com.AppRH.AppRH.service;

import com.AppRH.AppRH.models.Usuario;

public interface SegurancaService {
	
	public Usuario criarUsuario(String nome, String senha, String email, String autorizacao);

}
