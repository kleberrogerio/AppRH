package com.AppRH.AppRH.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.AppRH.AppRH.models.Cooperado;
import com.AppRH.AppRH.models.Dividas;

@Repository
public interface DividasRepository extends CrudRepository<Dividas, Long> {
	Iterable<Dividas>findByCooperado(Cooperado cooperado);

	//@Query(value = "select u from Dividas u where u.coopdatapagamento is null%")
	//List<Dividas>findByCoopdividasCooperado(Date coopdatapagamento);
}
