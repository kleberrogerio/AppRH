package com.AppRH.AppRH.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.AppRH.AppRH.dao.CooperadoDao;
import com.AppRH.AppRH.models.Cooperado;
import com.AppRH.AppRH.repository.CooperadoRepository;
import com.AppRH.AppRH.util.PaginacaoUtil;


@Service @Transactional(readOnly=false)
public class CooperadoServiceImpl implements CooperadoService {
	
	@Autowired
	private CooperadoRepository cr;
	
	@Autowired
	private CooperadoDao cd;

	@Override
	public PaginacaoUtil<Cooperado> buscaPorPagina(int pagina) {
		
		return cd.buscaPaginada(pagina);
	}

}
