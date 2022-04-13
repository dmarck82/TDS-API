package br.edu.utfpr.tdsapi.tdsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.tdsapi.tdsapi.model.TipoLançamento;

public interface TipoLançamentoRepository extends JpaRepository <TipoLançamento, Long>{
    
}
