package com.AppRH.AppRH.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.AppRH.AppRH.models.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
	
	Funcionario findById(long id);
	Funcionario findByFuncnome(String funcnome);
	
	// Query para a busca
	@Query(value = "select u from Funcionario u where u.funcnome like %?1%")
	List<Funcionario>findByFuncnomes(String funcnome);

}