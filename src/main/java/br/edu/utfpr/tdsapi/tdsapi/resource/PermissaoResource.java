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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.utfpr.tdsapi.tdsapi.model.Permissao;
import br.edu.utfpr.tdsapi.tdsapi.repository.PermissaoRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("permissao")
public class PermissaoResource {

    @Autowired
    private PermissaoRepository permissaoRepository;

    @GetMapping
    public List<Permissao> listar(){
        return permissaoRepository.findAll();
    }
    
    @PostMapping
    public ResponseEntity<Permissao> permissao(@RequestBody Permissao permissao, HttpServletResponse response) {
        Permissao permissaoSalva = permissaoRepository.save(permissao);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(permissaoSalva
        .getcodigope()).toUri();
        response.setHeader("Location", uri.toASCIIString());
        
        return ResponseEntity.created(uri).body(permissaoSalva);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity <?> buscarPeloCodigo(@PathVariable Long codigo){
        Optional<Permissao> permissao = this.permissaoRepository.findById(codigo);

        return permissao.isPresent() ? ResponseEntity.ok(permissao) : ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{codigo}")
    public void deletarPeloCodigo(@PathVariable Long codigo){
        permissaoRepository.deleteById(codigo);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity <Permissao> alterarPeloCodigo(@RequestBody Permissao permissaoNovo, @PathVariable Long codigo){
        Optional<Permissao> permissaoVelho = permissaoRepository.findById(codigo);
        if(permissaoVelho.isPresent()){
            Permissao permissaoTemp = permissaoVelho.get();
            permissaoTemp.setdescricao(permissaoNovo.getdescricao());
            permissaoRepository.save(permissaoTemp);
            return new ResponseEntity<Permissao>(permissaoTemp, HttpStatus.OK);
        }
        else   
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    

}
