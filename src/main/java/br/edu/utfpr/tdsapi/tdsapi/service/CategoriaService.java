package br.edu.utfpr.tdsapi.tdsapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.edu.utfpr.tdsapi.tdsapi.model.Categoria;
import br.edu.utfpr.tdsapi.tdsapi.repository.CategoriaRepository;

@Service
public class CategoriaService {
    
    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria atualizar(@PathVariable Long codigo, @RequestBody Categoria categoria){
        
        Categoria categoriaSalva = this.categoriaRepository.findById(codigo).orElseThrow(() -> new EmptyResultDataAccessException(1));

        BeanUtils.copyProperties(categoria, categoriaSalva, "codigo");

        return this.categoriaRepository.save(categoriaSalva);
    }
    
}
