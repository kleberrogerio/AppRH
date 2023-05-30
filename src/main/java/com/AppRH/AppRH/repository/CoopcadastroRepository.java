package com.AppRH.AppRH.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.AppRH.AppRH.models.Coopcadastro;
import com.AppRH.AppRH.models.Cooperado;


@Repository
public interface CoopcadastroRepository extends CrudRepository<Coopcadastro, Long> {
	Iterable<Coopcadastro>findByCooperado(Cooperado cooperado);

}
