package br.com.app.resource;

import br.com.app.commons.ExampleValues;
import br.com.app.dto.EstudanteDTO;
import br.com.app.entity.Estudante;
import br.com.app.service.EstudanteService;
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
@RequestMapping("estudantes")
public class EstudanteResource {

    @Autowired
    private EstudanteService estudanteService;
    @Autowired
    private ModelMapper mapper;


    @GetMapping
    @Operation(description = "Retorna todos os estudantes")
    public ResponseEntity<List <EstudanteDTO>> buscarTodosEstudantes(){
        List<Estudante> listaEstudantes = estudanteService.buscarTodosEstudantes();
        List<EstudanteDTO> listaEstudantesDTO = listaEstudantes.stream().map(estudante -> mapper.map(estudante, EstudanteDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listaEstudantesDTO);

    }


    @GetMapping("/{id}")
    @Operation(description = "Retorna o registro  estudante pelo id")
    public ResponseEntity<EstudanteDTO> buscarEstudantePeloId(@PathVariable("id") @Schema(example = ExampleValues.ID_ESTUDANTE) Integer id){
        Estudante estudante = estudanteService.buscarEstudantePeloId(id);
        EstudanteDTO estudanteDTO = mapper.map(estudante, EstudanteDTO.class);
        return ResponseEntity.ok().body(estudanteDTO);
    }

    @PostMapping
    @Operation(description = "Cadastra um estudante")
    public ResponseEntity<EstudanteDTO> cadastrarEstudante(@Valid @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples ={@ExampleObject(name = "Exemplo de Estudante", value = ExampleValues.exemploJsonEstudante)}))
         @RequestBody EstudanteDTO estudanteDTO){
        Estudante estudante = mapper.map(estudanteDTO, Estudante.class);
        estudante = estudanteService.salvar(estudante);
        EstudanteDTO novoEstudante = mapper.map(estudante, EstudanteDTO.class);
        return ResponseEntity.ok().body(novoEstudante);
    }


    @PutMapping("/{id}")
    @Operation(description = "Altera o registro do estudante pelo id")
    public ResponseEntity<EstudanteDTO> atualizarEstudante(@PathVariable("id") @Schema(example = ExampleValues.ID_ESTUDANTE) Integer id, @Valid @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples ={@ExampleObject(name = "Exemplo de Estudante", value = ExampleValues.exemploJsonEstudante)}))
         @RequestBody EstudanteDTO estudanteDTO){
        Estudante estudante = mapper.map(estudanteDTO, Estudante.class);
        estudante = estudanteService.updateEstudante(id, estudante);
        EstudanteDTO novoEstudante = mapper.map(estudante, EstudanteDTO.class);
        return ResponseEntity.ok().body(novoEstudante);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Remover um  estudante pelo id")
    public ResponseEntity<Boolean> excluirEstudante(@PathVariable("id") @Schema(example = ExampleValues.ID_ESTUDANTE) Integer id){
        Boolean flag = estudanteService.deletarEstudante(id);
        return ResponseEntity.ok().body(flag);
    }

    //localhost:8080/estudantes/paginacao?pagina=0&itensPorPagina=2&ordenacao=nome&tipoOrdenacao=ASC
    @GetMapping("paginacao")
    public Page<Estudante> buscarEstudantesPorPaginacao
    (@RequestParam @Schema(example = ExampleValues.PAGINA) Integer pagina,
     @RequestParam @Schema(example = ExampleValues.ITENS_POR_PAGINA) Integer itensPorPagina,
     @RequestParam @Schema(example = ExampleValues.ORDENACAO) String ordenacao,
     @RequestParam @Schema(example = ExampleValues.TIPO_ORDERNACAO) String tipoOrdenacao){
        PageRequest page = PageRequest.of(pagina, itensPorPagina,(tipoOrdenacao.equals("ASC")? Sort.by(ordenacao).ascending():Sort.by(ordenacao).descending()));
        return estudanteService.buscarEstudantesPorPaginacao(page);
    }
}
