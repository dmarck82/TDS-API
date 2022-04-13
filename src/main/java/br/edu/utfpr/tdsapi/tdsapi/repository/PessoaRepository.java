package br.edu.utfpr.tdsapi.tdsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.tdsapi.tdsapi.model.Pessoa;

public interface PessoaRepository extends JpaRepository <Pessoa, Long>{
    
}
