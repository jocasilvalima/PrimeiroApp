package br.com.app.resource;

import br.com.app.commons.ExampleValues;
import br.com.app.dto.TurmaDTO;
import br.com.app.entity.Professor;
import br.com.app.entity.Turma;
import br.com.app.service.TurmaService;
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
@RequestMapping("turma")
public class TurmaResource {
    @Autowired
    private TurmaService turmaService;
    @Autowired
    private ModelMapper mapper;

    @GetMapping
    @Operation(description = "Retorna todas as turmas")
    public ResponseEntity<List<TurmaDTO>> buscarTodasTurmas() {
        List<Turma> listaTurmas = turmaService.buscarTodasTurmas();
       List<TurmaDTO> listaTurmaDTO = listaTurmas.stream().map(turma -> mapper.map(turma, TurmaDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listaTurmaDTO);
    }


    @GetMapping("/{numero}")
    @Operation(description = "Retorna o registro  da turma pelo numero")
    public ResponseEntity<TurmaDTO> buscarTurmaPorNumero(@PathVariable("numero") @Schema(example = ExampleValues.NUMERO_TURMA) String numero) {
        Turma turma = turmaService.buscarTurmaPorNumero(numero);
        TurmaDTO turmaDTO = mapper.map(turma, TurmaDTO.class);
        return ResponseEntity.ok().body(turmaDTO);
    }


    @PostMapping
    @Operation(description = "Cadastra uma turma")
    public ResponseEntity<TurmaDTO> cadastrarTurma(@Valid @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples = {@ExampleObject(name = "Exemplo de Turma", value = ExampleValues.exemploJsonTurma)})) @RequestBody TurmaDTO turmaDTO) {
        Turma turma = mapper.map(turmaDTO, Turma.class);
        turma = turmaService.salvar(turma);
        TurmaDTO novaTurma = mapper.map(turma, TurmaDTO.class);
        return ResponseEntity.ok().body(novaTurma);
    }


    @PutMapping("/{numero}")
    @Operation(description = "Altera o registro dA turma pelo numero")
    public ResponseEntity<TurmaDTO> atualizarTurma(@PathVariable("numero") @Schema(example = ExampleValues.NUMERO_TURMA) String numero, @Valid @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples = {@ExampleObject(name = "Exemplo de Turma", value = ExampleValues.exemploJsonTurma)})) @RequestBody TurmaDTO turmaDTO) {
        Turma turma = mapper.map(turmaDTO, Turma.class);
        turma = turmaService.updateTurma(numero, turma);
        TurmaDTO novaTurma = mapper.map(turma, TurmaDTO.class);
        return ResponseEntity.ok().body(novaTurma);
    }

    @DeleteMapping("/{numero}")
    @Operation(description = "Remove uma turma pelo numero")
    public ResponseEntity<Boolean> excluirTurma(@PathVariable("numero") @Schema(example = ExampleValues.NUMERO_TURMA) String numero) {
        Boolean flag = turmaService.deletarTurmaPorNumero(numero);
        return ResponseEntity.ok().body(flag);
    }

    //localhost:8080/estudantes/paginacao?pagina=0&itensPorPagina=2&ordenacao=nome&tipoOrdenacao=ASC
    @GetMapping("paginacao")
    public Page<Turma>buscarTurmaPorPaginacao
    (@RequestParam @Schema(example = ExampleValues.PAGINA) Integer pagina,
     @RequestParam @Schema(example = ExampleValues.ITENS_POR_PAGINA) Integer itensPorPagina,
     @RequestParam @Schema(example = ExampleValues.ORDENACAO) String ordenacao,
     @RequestParam @Schema(example = ExampleValues.TIPO_ORDERNACAO) String tipoOrdenacao) {
        PageRequest page = PageRequest.of(pagina, itensPorPagina, (tipoOrdenacao.equals("ASC") ? Sort.by(ordenacao).ascending() : Sort.by(ordenacao).descending()));
        return TurmaService.buscarTurmaPorPaginacao(page);
    }

}
