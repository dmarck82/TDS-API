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

import br.edu.utfpr.tdsapi.tdsapi.model.Usuario;
import br.edu.utfpr.tdsapi.tdsapi.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioResource {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Usuario> listar(){
        return usuarioRepository.findAll();
    }
    @PostMapping
    public ResponseEntity<Usuario> criar(@RequestBody Usuario usuario, HttpServletResponse response){
        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(usuarioSalvo
        .getcodigou()).toUri();
        response.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(usuarioSalvo);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity <?> buscarPeloCodigo(@PathVariable Long codigo){
        Optional<Usuario> usuario = this.usuarioRepository.findById(codigo);

        return usuario.isPresent() ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{codigo}")
    public void deletarPeloCodigo(@PathVariable Long codigo){
        usuarioRepository.deleteById(codigo);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity <Usuario> alterarPeloCodigo(@RequestBody Usuario usuarioNovo, @PathVariable Long codigo){
        Optional<Usuario> usuarioVelho = usuarioRepository.findById(codigo);
        if(usuarioVelho.isPresent()){
            Usuario usuarioTemp = usuarioVelho.get();
            usuarioTemp.setNome(usuarioNovo.getNome());
            usuarioTemp.setEmail(usuarioNovo.getEmail());
            usuarioTemp.setSenha(usuarioNovo.getSenha());
            usuarioTemp.setcodigopeu(usuarioNovo.getcodigopeu());
            usuarioRepository.save(usuarioTemp);
            return new ResponseEntity<Usuario>(usuarioTemp, HttpStatus.OK);
        }
        else   
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
