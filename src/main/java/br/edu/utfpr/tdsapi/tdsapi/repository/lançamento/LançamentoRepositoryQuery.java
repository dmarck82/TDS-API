package br.edu.utfpr.tdsapi.tdsapi.repository.lançamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.edu.utfpr.tdsapi.tdsapi.model.Lançamento;
import br.edu.utfpr.tdsapi.tdsapi.repository.filter.LançamentoFilter;

public interface LançamentoRepositoryQuery {
    
    public Page<Lançamento> filtrar(LançamentoFilter lançamentoFilter, Pageable pageable);
}
