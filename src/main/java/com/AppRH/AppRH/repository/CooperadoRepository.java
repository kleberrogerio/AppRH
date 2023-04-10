package com.AppRH.AppRH.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.AppRH.AppRH.models.Cooperado;

@Repository
public interface CooperadoRepository extends CrudRepository<Cooperado, Integer>{
		
	Cooperado findByCoopindexcod(long coop_index_cod);
	List<Cooperado> findByCoopnome(String coop_nome);

}
