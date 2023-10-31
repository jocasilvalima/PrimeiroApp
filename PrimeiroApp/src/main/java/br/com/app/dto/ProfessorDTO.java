package br.com.app.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class ProfessorDTO {

    @Getter
    @Setter
    private Integer matricula;


    @Getter
    @Setter
    @NotNull
    private String nome;


    @Getter
    @Setter
    @NotNull
    private String especialidade;


    @Getter
    @Setter
    @NotNull
    private String telefone;
}
