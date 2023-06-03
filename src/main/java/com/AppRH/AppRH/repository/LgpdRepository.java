package com.AppRH.AppRH.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.AppRH.AppRH.models.Cooperado;
import com.AppRH.AppRH.models.Lgpd;

@Repository
public interface LgpdRepository extends CrudRepository<Lgpd, Long> {

	Iterable<Lgpd>findByCooperado(Cooperado cooperado);
	Lgpd findByCoopmatricula(int coop_matricula);
	

}
