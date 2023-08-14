package io.github.rodrigodante11.projeto_spring_angular.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
@Builder
public class UsuarioDTO {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String senha;
    private boolean isAdmin;
}
