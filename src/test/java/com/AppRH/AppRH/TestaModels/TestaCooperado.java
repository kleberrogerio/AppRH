package com.AppRH.AppRH.TestaModels;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.AppRH.AppRH.models.Usuario;
import com.AppRH.AppRH.repository.UsuarioRepository;


@SpringBootTest
@Transactional
@Rollback
public class TestaCooperado {
	
	@Autowired
	private UsuarioRepository ur;

	@Test
	void testaInsercao() {
		Usuario usuario = new Usuario();
		usuario.setNome("Edirley");
		usuario.setSenha("Edirley");
		ur.save(usuario);
		
		assertNotNull(usuario.getId());
		
	}

}

