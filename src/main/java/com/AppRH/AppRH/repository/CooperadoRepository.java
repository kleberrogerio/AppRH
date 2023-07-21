package com.AppRH.AppRH.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.AppRH.AppRH.models.Cooperado;
//import com.AppRH.AppRH.util.PaginacaoUtil;

@Repository
public interface CooperadoRepository extends JpaRepository<Cooperado, Long>{
		
	//Cooperado findByCoopindexcod(int coop_index_cod);
	Cooperado findByCoopmatricula(int coop_matricula);
	List<Cooperado>findByCoopnome(String coop_nome);
	
	//Para Buscar
	@Query(value = "SELECT * FROM coop c", nativeQuery = true)
	List<Cooperado> encontrarTodos();
	//Preparando para paginar
	//List<Cooperado> encontrarTodos(Pageable pageable);
	
	@Query(value = "SELECT * FROM coop c, coop_cadastro e WHERE c.coop_matricula = e.coop_matricula AND e.coop_cooperado = 'ATIVO'", nativeQuery = true)
	List<Cooperado> encontrarAtivos();
	
	@Query(value = "SELECT * FROM coop c, coop_cadastro e WHERE c.coop_matricula = e.coop_matricula AND e.coop_cooperado = 'NAO'", nativeQuery = true)
	List<Cooperado> encontrarInativos();
		
	@Query(value = "select u from Cooperado u where u.coopnome like %?1%")
	List<Cooperado>findByCoopnomesCooperado(String coopnome);
	
	@Query(value = "SELECT * FROM coop c WHERE (c.coop_nome like CONCAT('%' :param '%') OR c.coop_matricula = :param OR c.coop_nome_guerra LIKE CONCAT('%' :param '%'))", nativeQuery = true)
	List<Cooperado>findByCoopnomesCooperados(@Param(value = "param") String coopnome);
		
	
	@Query(value = "SELECT * FROM coop c, coop_cadastro e WHERE c.coop_matricula = e.coop_matricula AND e.coop_cooperado = 'ATIVO' And (c.coop_nome LIKE CONCAT('%':param '%') OR c.coop_matricula = :param OR c.coop_nome_guerra LIKE CONCAT('%' :param '%'))", nativeQuery = true)
	List<Cooperado>findByCoopnomesCooperadoAtivo(@Param(value = "param") String coopnome);

	@Query(value = "SELECT * FROM coop c, coop_cadastro e WHERE c.coop_matricula = e.coop_matricula AND e.coop_cooperado = 'NAO' And (c.coop_nome LIKE CONCAT('%' :param '%') OR c.coop_matricula = :param OR c.coop_nome_guerra LIKE CONCAT('%' :param '%'))", nativeQuery = true)
	List<Cooperado>findByCoopnomesCooperadoInativo(@Param(value = "param") String coopnome);
	
	@Query(value="SELECT Max(c.coop_matricula) from coop c", nativeQuery = true)
	Integer achaMaior();
	
	//Busca para etiquetas
	@Query(value = "SELECT coop_matricula FROM coop order by coop_matricula asc", nativeQuery = true)
	List<Long> encontrarMatriculas();
	
}
