package com.AppRH.AppRH.service;

import java.util.List;

import com.AppRH.AppRH.models.Usuario;

public interface SegurancaService {
	
	public Usuario criarUsuario(String nome, String senha, String email, String autorizacao);
	
	public List<Usuario> buscarTodosUsuarios();
	
	public Usuario buscarUsuarioPorId(Long id);
	
	public Usuario buscarUsuarioPorNome(String nome);
	

}
