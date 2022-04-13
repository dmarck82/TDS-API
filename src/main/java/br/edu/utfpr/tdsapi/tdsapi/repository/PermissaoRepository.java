package br.edu.utfpr.tdsapi.tdsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.tdsapi.tdsapi.model.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long>{
    
}
