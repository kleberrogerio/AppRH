package com.AppRH.AppRH.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.AppRH.AppRH.models.Cooperado;
import com.AppRH.AppRH.models.Cotaparte;

@Repository
public interface CotaRepository extends CrudRepository<Cotaparte, Long> {
	Iterable<Cotaparte>findByCooperado(Cooperado cooperado);

}
