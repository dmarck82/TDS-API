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
import br.edu.utfpr.tdsapi.tdsapi.model.Pessoa;
import br.edu.utfpr.tdsapi.tdsapi.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {
        
    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<Pessoa> listar(){
        return pessoaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Pessoa>  criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response){
       
        Pessoa pessoaSalva = pessoaRepository.save(pessoa);

         publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getcodigop()));

        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);

    }

    @GetMapping("/{codigo}")
    public ResponseEntity <?>  buscarPeloCodigo(@PathVariable Long codigo) {
        
        Optional<Pessoa> pessoa = pessoaRepository.findById(codigo);

        return pessoa != null ? ResponseEntity.ok(pessoa) : ResponseEntity.notFound().build();
        
    }

    @DeleteMapping("/{codigo}")
    public void deletarPeloCodigo(@PathVariable Long codigo){
        
        pessoaRepository.deleteById(codigo);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity <Pessoa> alterarPeloCodigo(@RequestBody Pessoa pessoaNova, @PathVariable Long codigo){
        
        Optional<Pessoa> pessoaVelha = pessoaRepository.findById(codigo);

        if(pessoaVelha.isPresent()){
            Pessoa pessoaTemp = pessoaVelha.get();
            pessoaTemp.setNome(pessoaNova.getNome());
            pessoaTemp.setAtivo(pessoaNova.getAtivo());
            pessoaTemp.setEndereço(pessoaNova.getEndereço());
            pessoaRepository.save(pessoaTemp);
            return new ResponseEntity<Pessoa>(pessoaTemp, HttpStatus.OK);
        }
        else   
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
