package io.github.rodrigodante11.projeto_spring_angular.service.imp;

import io.github.rodrigodante11.projeto_spring_angular.entity.Usuario;
import io.github.rodrigodante11.projeto_spring_angular.exception.ErroUsuarioException;
import io.github.rodrigodante11.projeto_spring_angular.repository.UsuarioRepository;
import io.github.rodrigodante11.projeto_spring_angular.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
// import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImp implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    // private PasswordEncoder passwordEncoder;

    /*
    public void cripografarSenha(Usuario usuario){

        // criptografando a senha
        String senha = usuario.getSenha();
        String senhaCripto = passwordEncoder.encode(senha);
        usuario.setSenha(senhaCripto);

    } */

    @Override
    @Transactional
    public Usuario salvar(Usuario usuario) {
        // cripografarSenha(usuario);
        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public void deletar(Usuario usuario) {
        Objects.requireNonNull(usuario.getId());
        usuarioRepository.delete(usuario);

    }

    @Override
    public Optional<Usuario> obterPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    @Transactional
    public Usuario autenticar(String email, String senha) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

        if( usuario.isEmpty()){
            throw new ErroUsuarioException("Usuario não encontrado pelo email informado");
        }

        /*
        boolean senhaBatem = passwordEncoder.matches(senha, usuario.get().getSenha());
        if(!senhaBatem){
            throw new ErroUsuarioException("Senha inválida");
        }*/

        return usuario.get();

    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> buscar(Usuario usuarioFiltro) {

        return usuarioRepository
                .findByEmailContainingIgnoreCaseAndNomeContainingIgnoreCase(
                        usuarioFiltro.getNome(), usuarioFiltro.getEmail()
                );
    }

    @Override
    public List<Usuario> obterTodos() {
        return usuarioRepository.findAll();

    }
}
