package com.AppRH.AppRH.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AppRH.AppRH.models.Interessado;

public interface InteressadoRepository extends JpaRepository<Interessado, Long>{
	
	//List<Interessado>findByCoopnome(String coop_nome);

}
