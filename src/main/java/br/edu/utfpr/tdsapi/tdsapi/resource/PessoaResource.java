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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.edu.utfpr.tdsapi.tdsapi.event.RecursoCriadoEvent;
import br.edu.utfpr.tdsapi.tdsapi.model.Pessoa;
import br.edu.utfpr.tdsapi.tdsapi.repository.PessoaRepository;
import br.edu.utfpr.tdsapi.tdsapi.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {
        
    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private PessoaService pessoaService;

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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarPeloCodigo(@PathVariable Long codigo){
        
        pessoaRepository.deleteById(codigo);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Pessoa> atualizar(@PathVariable Long codigo, @Valid @RequestBody Pessoa pessoa){
        
        Pessoa pessoaSalva = pessoaService.atualizar(codigo, pessoa);
        return ResponseEntity.ok(pessoaSalva);
    }
    
    @PutMapping("/{codigo}/ativo")
    public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo){
        
        pessoaService.atualizarPropriedadeAtivo(codigo, ativo);
    }

}
