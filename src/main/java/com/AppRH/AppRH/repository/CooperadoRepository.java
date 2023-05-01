package com.AppRH.AppRH.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.AppRH.AppRH.models.Cooperado;

@Repository
public interface CooperadoRepository extends CrudRepository<Cooperado, Long>{
		
	Cooperado findByCoopindexcod(int coop_index_cod);
	Cooperado findByCoopmatricula(int coop_matricula);
	List<Cooperado>findByCoopnome(String coop_nome);
	
	//Para Buscar
	@Query(value = "select u from Cooperado u where u.coopnome like %?1%")
	List<Cooperado>findByCoopnomesCooperado(String coopnome);
	
}
