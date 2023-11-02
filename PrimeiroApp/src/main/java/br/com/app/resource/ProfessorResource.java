package br.com.app.resource;

import br.com.app.commons.ExampleValues;
import br.com.app.dto.ProfessorDTO;
import br.com.app.entity.Professor;
import br.com.app.service.ProfessorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("professor")
public class ProfessorResource {

    @Autowired
    private ProfessorService professorService;
    @Autowired
    private ModelMapper mapper;


    @GetMapping
    @Operation(description = "Retorna todos os professores")
    public ResponseEntity<List<ProfessorDTO>> buscarTodosProfessores() {
        List<Professor> listaProfessores = professorService.buscarTodosProfessores();
        List<ProfessorDTO> listaProfessorDTO = listaProfessores.stream().map(professor -> mapper.map(professor, ProfessorDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listaProfessorDTO);

    }


    @GetMapping("/{matricula}")
    @Operation(description = "Retorna o registro  do Professor pela Matricula")
    public ResponseEntity<ProfessorDTO> buscarProfessorPorMatricula(@PathVariable("matricula") @Schema(example = ExampleValues.MATRICULA_PROFESSOR) Integer matricula) {
        Professor professor = professorService.buscarProfessorPorMatricula(matricula);
        ProfessorDTO professorDTO = mapper.map(professor, ProfessorDTO.class);
        return ResponseEntity.ok().body(professorDTO);
    }

    @PostMapping
    @Operation(description = "Cadastra um professor")
    public ResponseEntity<ProfessorDTO> cadastrarProfessor(@Valid @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples = {@ExampleObject(name = "Exemplo de Professor", value = ExampleValues.exemploJsonProfessor)})) @RequestBody ProfessorDTO professorDTO) {
        Professor professor = mapper.map(professorDTO, Professor.class);
        professor = professorService.salvar(professor);
        ProfessorDTO novoProfessor = mapper.map(professor, ProfessorDTO.class);
        return ResponseEntity.ok().body(novoProfessor);
    }


    @PutMapping("/{matricula}")
    @Operation(description = " Professor pela Matricula")
    public ResponseEntity<ProfessorDTO> atualizarProfessor(@PathVariable("matricula") @Schema(example = ExampleValues.MATRICULA_PROFESSOR) Integer matricula, @Valid @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples = {@ExampleObject(name = "Exemplo de Professor", value = ExampleValues.exemploJsonProfessor)})) @RequestBody ProfessorDTO professorDTO) {
        Professor professor = mapper.map(professorDTO, Professor.class);
        professor = professorService.updateProfessor(matricula, professor);
        ProfessorDTO novoProfessor = mapper.map(professor, ProfessorDTO.class);
        return ResponseEntity.ok().body(novoProfessor);
    }

    @DeleteMapping("/{matricula}")
    @Operation(description = "Remover um  professor pela Matricula")
    public ResponseEntity<Boolean> excluirProfessor(@PathVariable("matricula") @Schema(example = ExampleValues.MATRICULA_PROFESSOR) Integer matricula) {
        Boolean flag = professorService.deletarProfessor(matricula);
        return ResponseEntity.ok().body(flag);
    }

    //localhost:8080/estudantes/paginacao?pagina=0&itensPorPagina=2&ordenacao=nome&tipoOrdenacao=ASC
    @GetMapping("paginacao")
    public Page<Professor>buscarProfessoresPorPaginacao
    (@RequestParam @Schema(example = ExampleValues.PAGINA) Integer pagina,
     @RequestParam @Schema(example = ExampleValues.ITENS_POR_PAGINA) Integer itensPorPagina,
     @RequestParam @Schema(example = ExampleValues.ORDENACAO) String ordenacao,
     @RequestParam @Schema(example = ExampleValues.TIPO_ORDERNACAO) String tipoOrdenacao) {
        PageRequest page = PageRequest.of(pagina, itensPorPagina, (tipoOrdenacao.equals("ASC") ? Sort.by(ordenacao).ascending() : Sort.by(ordenacao).descending()));
        return professorService.buscarProfessoresPorPaginacao(page);
    }
}