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
import br.edu.utfpr.tdsapi.tdsapi.model.Lançamento;
import br.edu.utfpr.tdsapi.tdsapi.repository.LançamentoRepository;

@RestController
@RequestMapping("/lançamentos")

public class LançamentoResource {

    @Autowired
    private LançamentoRepository lançamentoRepository;

    @Autowired
    private ApplicationEventPublisher publisher;


    @GetMapping
    public List<Lançamento> listar() {
        return lançamentoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Lançamento> criar(@Valid @RequestBody Lançamento lançamento, HttpServletResponse response){
        
        Lançamento lançamentoSalvo = lançamentoRepository.save(lançamento);
        
        publisher.publishEvent(new RecursoCriadoEvent(this, response, lançamentoSalvo.getcodigol()));

        return ResponseEntity.status(HttpStatus.CREATED).body(lançamentoSalvo);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity <?> buscarPeloCodigo(@PathVariable Long codigo){
        
        Optional<Lançamento> lançamento= this.lançamentoRepository.findById(codigo);
        
        return lançamento.isPresent() ? ResponseEntity.ok(lançamento) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{codigo}")
    public void deletarPeloCodigo(@PathVariable Long codigo){
        
        lançamentoRepository.deleteById(codigo);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity <Lançamento> alterarPeloCodigo(@RequestBody Lançamento lançamentoNovo, @PathVariable Long codigo){
        
        Optional<Lançamento> lançamentoVelho = lançamentoRepository.findById(codigo);
        
        if(lançamentoVelho.isPresent()){
            Lançamento lançamentoTemp = lançamentoVelho.get();
            lançamentoTemp.setDescricao(lançamentoNovo.getDescricao());
            lançamentoTemp.setDatavencimento(lançamentoNovo.getDatavencimento());
            lançamentoTemp.setDatapagamento(lançamentoNovo.getDatapagamento());
            lançamentoTemp.setValor(lançamentoNovo.getValor());
            lançamentoTemp.setObservacao(lançamentoNovo.getObservacao());            
            lançamentoRepository.save(lançamentoTemp);
            return new ResponseEntity<Lançamento>(lançamentoTemp, HttpStatus.OK);
        }
        else   
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
