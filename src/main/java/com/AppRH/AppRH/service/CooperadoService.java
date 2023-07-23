package com.AppRH.AppRH.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.AppRH.AppRH.models.Cooperado;
import com.AppRH.AppRH.util.PaginacaoUtil;

public interface CooperadoService {
	
	PaginacaoUtil<Cooperado> buscarPorPagina(int pagina);
	
	public Page<Cooperado> getCooperadosPaginados(int pageNum, int pageSize);

}
