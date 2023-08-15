package io.github.rodrigodante11.projeto_spring_angular.controller;

import io.github.rodrigodante11.projeto_spring_angular.DTO.UsuarioDTO;
import io.github.rodrigodante11.projeto_spring_angular.Util.Converter;
import io.github.rodrigodante11.projeto_spring_angular.entity.Usuario;
import io.github.rodrigodante11.projeto_spring_angular.exception.ErroUsuarioException;
import io.github.rodrigodante11.projeto_spring_angular.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping()
    public ResponseEntity<?> salvarUsuario(@RequestBody UsuarioDTO usuarioDTO){

        try{
            Usuario usuario = Converter.usuarioConverter(usuarioDTO);


            return new ResponseEntity<>(usuario,HttpStatus.CREATED);
        }catch (ErroUsuarioException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/autenticar")
    public ResponseEntity autenticarUsuario(@RequestBody @Valid UsuarioDTO usuarioDTO){

        try{
            Usuario usuarioAutenticado = usuarioService.autenticar(usuarioDTO.getEmail(), usuarioDTO.getSenha());

            return ResponseEntity.ok(usuarioAutenticado);

        }catch(ErroUsuarioException e){
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deletarUsuario(@PathVariable("id") Long id ){

        return usuarioService.obterPorId(id).map( usuario -> {

            usuarioService.deletar(usuario);
            return new ResponseEntity<>("Usuario "+ usuario.getNome() +" Deletado Com Sucesso",
                    HttpStatus.NO_CONTENT);

        }).orElseGet( () ->
                new ResponseEntity<>("Usuario nao encontrado na base de dados", HttpStatus.BAD_REQUEST)
        );

    }

    @GetMapping("{id}")
    public ResponseEntity obterUsuarioPorId(@PathVariable("id") Long id ){
        return usuarioService.obterPorId(id)
                .map( usuario -> new ResponseEntity(
                                Converter.usuarioConverter(usuario),
                                HttpStatus.OK
                        )
                ).orElseGet( () -> new ResponseEntity<>("Usuario Não encontrado", HttpStatus.NOT_FOUND ));
    }

    @GetMapping()
    public ResponseEntity obterTodosUsuarios(){
        try {
            List<Usuario> usuarioList = usuarioService.obterTodos();
            return new ResponseEntity<>(usuarioList, HttpStatus.OK);
        }catch (ErroUsuarioException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/usuario_filtro")
    public ResponseEntity usuario_filtro(@RequestBody UsuarioDTO usuarioDTO){
        try {
            Usuario usuario = Converter.usuarioConverter(usuarioDTO);
            List<Usuario> usuarioList = usuarioService.buscar(usuario);
            return new ResponseEntity<>(usuarioList, HttpStatus.OK);
        }catch (ErroUsuarioException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
