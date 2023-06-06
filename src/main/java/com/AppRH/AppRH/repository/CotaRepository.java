package com.AppRH.AppRH.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.AppRH.AppRH.models.Cooperado;
import com.AppRH.AppRH.models.Cotaparte;

@Repository
public interface CotaRepository extends CrudRepository<Cotaparte, Long> {
	Iterable<Cotaparte>findByCooperado(Cooperado cooperado);

	//Para Buscar
		@Query(value = "SELECT * FROM coop_cota_parte where coop_datapagamento is not null", nativeQuery = true)
		List<Cotaparte> encontrarTodos();
}
