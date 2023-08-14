package io.github.rodrigodante11.projeto_spring_angular.Util;

import io.github.rodrigodante11.projeto_spring_angular.DTO.UsuarioDTO;
import io.github.rodrigodante11.projeto_spring_angular.entity.Usuario;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Converter {

    public static UsuarioDTO usuarioConverter(Usuario usuario){
        return UsuarioDTO.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .senha(usuario.getSenha())
                .email(usuario.getEmail())
                .telefone(usuario.getTelefone())
                .isAdmin(usuario.isAdmin())
                .build();
    }

    public static Usuario usuarioConverter(UsuarioDTO usuarioDTO){
        return Usuario.builder()
                .id(usuarioDTO.getId())
                .nome(usuarioDTO.getNome())
                .senha(usuarioDTO.getSenha())
                .email(usuarioDTO.getEmail())
                .telefone(usuarioDTO.getTelefone())
                .isAdmin(usuarioDTO.isAdmin())
                .build();
    }

}
