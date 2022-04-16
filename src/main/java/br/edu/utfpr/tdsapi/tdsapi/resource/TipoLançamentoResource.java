package br.edu.utfpr.tdsapi.tdsapi.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.edu.utfpr.tdsapi.tdsapi.event.RecursoCriadoEvent;
import br.edu.utfpr.tdsapi.tdsapi.model.TipoLançamento;
import br.edu.utfpr.tdsapi.tdsapi.repository.TipoLançamentoRepository;

@RestController
@RequestMapping("/tipolançamento")
public class TipoLançamentoResource {
    
    @Autowired
    private TipoLançamentoRepository tipoLançamentoRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<TipoLançamento> lista() {
        return tipoLançamentoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<TipoLançamento> criar(@Valid @RequestBody TipoLançamento tipoLançamento, HttpServletResponse response){
        
        TipoLançamento tipoLançamentoSalvo = tipoLançamentoRepository.save(tipoLançamento);
        
        publisher.publishEvent(new RecursoCriadoEvent(this, response, tipoLançamentoSalvo.getcodigotl()));

        return ResponseEntity.status(HttpStatus.CREATED).body(tipoLançamentoSalvo);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity <?> buscarPeloCodigo(@PathVariable Long codigo){
        
        Optional<TipoLançamento> tipoLançamento = this.tipoLançamentoRepository.findById(codigo);

        return tipoLançamento.isPresent() ? ResponseEntity.ok(tipoLançamento) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{codigo}")
    public void deletarPeloCodigo(@PathVariable Long codigo){
        
        tipoLançamentoRepository.deleteById(codigo);
    }
    @PutMapping("/{codigo}")
    public ResponseEntity <TipoLançamento> alterarPeloCodigo(@RequestBody TipoLançamento tipoLançamentoNovo, @PathVariable Long codigo){
        
        Optional<TipoLançamento> tipoLançamentoVelho = tipoLançamentoRepository.findById(codigo);
        
        if(tipoLançamentoVelho.isPresent()){
            TipoLançamento tipoLançamentoTemp = tipoLançamentoVelho.get();
            tipoLançamentoTemp.setReceita(tipoLançamentoTemp.getReceita());
            tipoLançamentoTemp.setDespesa(tipoLançamentoTemp.getDespesa());
            tipoLançamentoRepository.save(tipoLançamentoTemp);
            return new ResponseEntity<TipoLançamento>(tipoLançamentoTemp, HttpStatus.OK);
        }
        else   
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
