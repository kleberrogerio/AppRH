package com.AppRH.AppRH.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.AppRH.AppRH.models.Cooperado;

import com.AppRH.AppRH.models.Telefone;

@Repository
public interface TelefoneRepository extends CrudRepository<Telefone, Long>{
	
	Iterable<Telefone>findByCooperado(Cooperado cooperado);
	
	Telefone findByCoopmatricula(Long coop_matricula);
	
	Telefone findByCooptelindexcod(Long coop_tel_index_cod);
	
	Telefone findBycooptelnumero(String coop_tel_numero);
	
	List<Telefone>findByCoopteltipo(String coop_tel_tipo);

}
