package com.AppRH.AppRH.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.AppRH.AppRH.models.Autorizacao;
import com.AppRH.AppRH.models.Usuario;
import com.AppRH.AppRH.repository.AutorizacaoRepository;
import com.AppRH.AppRH.repository.UsuarioRepository;

@Service("segurancaService")
public class SegurancaServiceImpl implements SegurancaService{
	
	@Autowired
	private AutorizacaoRepository ar;
	
	@Autowired
	private UsuarioRepository ur;

	@Transactional
	public Usuario criarUsuario(String nome, String senha, String email, String autorizacao) {
		Autorizacao aut = ar.findByNome(autorizacao);
		if(aut == null) {
			aut = new Autorizacao();
			aut.setNome(autorizacao);
			ar.save(aut);
		}
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuario.setSenha(senha);
		usuario.setEmail(email);
		usuario.setAutorizacoes(new HashSet<Autorizacao>());
		usuario.getAutorizacoes().add(aut);
		ur.save(usuario);
		return usuario;
	}
	
	@Override
	public List<Usuario> buscarTodosUsuarios(){
		return ur.findAll();
	}
	
	@Override
	public Usuario buscarUsuarioPorId(Long id){
		Optional<Usuario> usuarioOp = ur.findById(id);
		if(usuarioOp.isPresent()) {
			return usuarioOp.get();
		}
		throw new RuntimeException("Usuário não encontrado!");				
	}
	
	@Override
	public Usuario buscarUsuarioPorNome(String nome) {
		Usuario usuario = ur.findByNome(nome);
		if(usuario != null) {
			return usuario;
		}
		throw new RuntimeException("Usuario não encontrado");
		
	}

}
