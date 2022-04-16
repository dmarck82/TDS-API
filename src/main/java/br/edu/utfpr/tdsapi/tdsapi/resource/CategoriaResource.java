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
import br.edu.utfpr.tdsapi.tdsapi.model.Categoria;
import br.edu.utfpr.tdsapi.tdsapi.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
    
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ApplicationEventPublisher publisher;
    
    @GetMapping
    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria, HttpServletResponse response){
        
        Categoria categoriaSalva = categoriaRepository.save(categoria);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getcodigoc()));

        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity <?> buscarPeloCodigo(@PathVariable Long codigo){
        
        Optional<Categoria> categoria = this.categoriaRepository.findById(codigo);

        return categoria.isPresent() ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{codigo}")
    public void deletarPeloCodigo(@PathVariable Long codigo){
        
        categoriaRepository.deleteById(codigo);

    }

    @PutMapping("/{codigo}")
    public ResponseEntity <Categoria> alterarPeloCodigo(@RequestBody Categoria categoriaNova, @PathVariable Long codigo){
        
        Optional<Categoria> categoriaVelha = categoriaRepository.findById(codigo);
        
        if(categoriaVelha.isPresent()){
            Categoria categoriaTemp = categoriaVelha.get();
            categoriaTemp.setNome(categoriaNova.getNome());
            categoriaRepository.save(categoriaTemp);
            return new ResponseEntity<Categoria>(categoriaTemp, HttpStatus.OK);
        }
        else   
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
