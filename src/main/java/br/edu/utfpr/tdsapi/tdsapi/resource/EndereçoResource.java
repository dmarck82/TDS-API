package br.edu.utfpr.tdsapi.tdsapi.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.utfpr.tdsapi.tdsapi.model.Endereço;
import br.edu.utfpr.tdsapi.tdsapi.repository.EndereçoRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/endereço")
public class EndereçoResource {
    
    @Autowired
    private EndereçoRepository endereçoRepository;

    @GetMapping
    public List<Endereço> listar(){
        return endereçoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Endereço> criar(@Valid @RequestBody Endereço endereço, HttpServletResponse response) {
        Endereço endereçoSalvo = endereçoRepository.save(endereço);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(endereçoSalvo
        .getcodigoe()).toUri();
        response.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(endereçoSalvo);
    }
    
    @GetMapping("/{codigo}")
    public ResponseEntity <?> buscarPeloCodigo(@PathVariable Long codigo){
        Optional<Endereço> endereço = this.endereçoRepository.findById(codigo);
        return endereço.isPresent() ? ResponseEntity.ok(endereço) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{codigo}")
    public void deletarPeloCodigo(@PathVariable Long codigo){
        endereçoRepository.deleteById(codigo);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity <Endereço> alterarPeloCodigo(@RequestBody Endereço endereçoNovo, @PathVariable Long codigo){
        Optional<Endereço> endereçoVelho = endereçoRepository.findById(codigo);
        if(endereçoVelho.isPresent()){
            Endereço endereçoTemp = endereçoVelho.get();
            endereçoTemp.setLogradouro(endereçoNovo.getLogradouro());
            endereçoTemp.setNumero(endereçoNovo.getNumero());
            endereçoTemp.setComplemento(endereçoNovo.getComplemento());
            endereçoTemp.setBairro(endereçoNovo.getBairro());
            endereçoTemp.setCep(endereçoNovo.getCep());
            endereçoTemp.setCidade(endereçoNovo.getCidade());
            endereçoTemp.setEstado(endereçoNovo.getEstado());
            endereçoTemp.setcodigope(endereçoNovo.getcodigope());
            endereçoRepository.save(endereçoTemp);
            return new ResponseEntity<Endereço>(endereçoTemp, HttpStatus.OK);
        }
        else   
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
