package br.com.app.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;

public class EstudanteDTO {
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    @NotNull
    private String nome;

    @Getter
    @Setter
    @NotNull
    private String sobrenome;


    @Getter
    @Setter
    @NotNull
    private String email;


    @Getter
    @Setter
    private LocalDate dataNascimento;


}
