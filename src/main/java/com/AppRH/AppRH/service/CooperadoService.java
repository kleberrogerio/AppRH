package com.AppRH.AppRH.service;

import com.AppRH.AppRH.models.Cooperado;
import com.AppRH.AppRH.util.PaginacaoUtil;

public interface CooperadoService {
	
	PaginacaoUtil<Cooperado> buscaPorPagina(int pagina);

}
