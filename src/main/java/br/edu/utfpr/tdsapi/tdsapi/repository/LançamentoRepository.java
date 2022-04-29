package br.edu.utfpr.tdsapi.tdsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.tdsapi.tdsapi.model.Lançamento;
import br.edu.utfpr.tdsapi.tdsapi.repository.lançamento.LançamentoRepositoryQuery;

public interface LançamentoRepository  extends JpaRepository <Lançamento, Long>, LançamentoRepositoryQuery<Lançamento>{
    
}
