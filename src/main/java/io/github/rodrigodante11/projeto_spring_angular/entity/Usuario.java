package io.github.rodrigodante11.projeto_spring_angular.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table( name="usuario")
public class Usuario {

    @Id
    @Column(name="id", unique = true )
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( nullable = false)
    private String nome;

    @Column()
    private String telefone;

    @Column( nullable = false, unique = true)
    private String email;

    @Column( nullable = false)
    private String senha;

    @Column(name = "data_cadastro", updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @Column(name = "is_admin")
    private boolean isAdmin;

    @PrePersist
    public  void prePersisist(){
        setDataCadastro(LocalDate.now());
    }
}
