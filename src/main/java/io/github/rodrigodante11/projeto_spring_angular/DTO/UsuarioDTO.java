package io.github.rodrigodante11.projeto_spring_angular.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
public class UsuarioDTO {

    private Long id;

    @NotNull(message = "{ campo.nome.obrigatorio }")
    private String nome;

    @NotNull(message = "{campo.email.obrigatorio}")
    private String email;

    private String telefone;

    @NotNull(message = "{campo.senha.obrigatorio}")
    private String senha;

    private boolean isAdmin;
}
