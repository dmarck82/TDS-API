package br.edu.utfpr.tdsapi.tdsapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.edu.utfpr.tdsapi.tdsapi.model.Lançamento;
import br.edu.utfpr.tdsapi.tdsapi.repository.LançamentoRepository;

@Service
public class LançamentoService {
    
    @Autowired
    private LançamentoRepository lançamentoRepository;
    
    public Lançamento atualizar(@PathVariable Long codigo, @RequestBody Lançamento lançamento){
        
        Lançamento lançamentoSalvo = this.lançamentoRepository.findById(codigo).orElseThrow(() -> new EmptyResultDataAccessException(1));

        BeanUtils.copyProperties(lançamento, lançamentoSalvo, "codigo");
        
        return this.lançamentoRepository.save(lançamentoSalvo);
    }
}
