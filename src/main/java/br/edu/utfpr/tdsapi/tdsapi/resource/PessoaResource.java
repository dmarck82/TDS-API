package br.edu.utfpr.tdsapi.tdsapi.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.utfpr.tdsapi.tdsapi.model.Pessoa;
import br.edu.utfpr.tdsapi.tdsapi.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {
        
    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping
    public List<Pessoa> listar(){
        return pessoaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Pessoa>  criar(@RequestBody Pessoa pessoa, HttpServletResponse response){
        Pessoa pessoaSalva = pessoaRepository.save(pessoa);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(pessoa
        .getcodigop()).toUri();

        return ResponseEntity.created(uri).body(pessoaSalva);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity <?>  buscarPeloCodigo(@PathVariable Long codigo) {
        Optional<Pessoa> pessoa = this.pessoaRepository.findById(codigo);

        return pessoa.isPresent() ? ResponseEntity.ok(pessoa) : ResponseEntity.notFound().build();
        
    }

    @DeleteMapping("/{codigo}")
    public void deletarPeloCodigo(@PathVariable Long codigo){
        pessoaRepository.deleteById(codigo);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity <Pessoa> alterarPeloCodigo(@RequestBody Pessoa PessoaNovo, @PathVariable Long codigo){
        Optional<Pessoa> PessoaVelho = pessoaRepository.findById(codigo);
        if(PessoaVelho.isPresent()){
            Pessoa PessoaTemp = PessoaVelho.get();
            PessoaTemp.setNome(PessoaNovo.getNome());
            PessoaTemp.setAtivo(PessoaNovo.getAtivo());
            pessoaRepository.save(PessoaTemp);
            return new ResponseEntity<Pessoa>(PessoaTemp, HttpStatus.OK);
        }
        else   
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
