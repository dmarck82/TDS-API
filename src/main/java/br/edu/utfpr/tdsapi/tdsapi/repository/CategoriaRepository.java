package br.edu.utfpr.tdsapi.tdsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.tdsapi.tdsapi.model.Categoria;

public interface CategoriaRepository extends JpaRepository <Categoria, Long>{
    
}
