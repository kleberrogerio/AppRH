package com.AppRH.AppRH.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.AppRH.AppRH.models.Cooperado;
import com.AppRH.AppRH.models.Coopendereco;

@Repository
public interface EnderecoRepository extends CrudRepository<Coopendereco, Long> {
	Iterable<Coopendereco>findByCooperado(Cooperado cooperado);

}
