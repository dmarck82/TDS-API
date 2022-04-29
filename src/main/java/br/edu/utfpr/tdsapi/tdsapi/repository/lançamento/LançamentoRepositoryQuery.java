package br.edu.utfpr.tdsapi.tdsapi.repository.lançamento;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.edu.utfpr.tdsapi.tdsapi.repository.filter.LançamentoFilter;

public interface LançamentoRepositoryQuery<Lançamento> {
    
    public Page<Lançamento> filtrar(LançamentoFilter lançamentoFilter, Pageable pageable);
}
