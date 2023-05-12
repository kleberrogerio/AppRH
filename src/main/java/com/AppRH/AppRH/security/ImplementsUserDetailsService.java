/*package com.AppRH.AppRH.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.AppRH.AppRH.models.Usuario;
import com.AppRH.AppRH.repository.UsuarioRepository;

public class ImplementsUserDetailsService implements UserDetailsService{

	@Autowired
	private UsuarioRepository ur;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Usuario usuario = ur.findByLogin(login);
		
		if(usuario ==null) {
			throw new UsernameNotFoundException("Usuário não encontrado!!");
		}
		return usuario;
	}

}

*/