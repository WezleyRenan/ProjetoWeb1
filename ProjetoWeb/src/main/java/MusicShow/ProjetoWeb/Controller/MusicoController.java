package MusicShow.ProjetoWeb.Controller;

import MusicShow.ProjetoWeb.Dto.ListaMusicosDto;
import MusicShow.ProjetoWeb.Dto.MusicoDto;
import MusicShow.ProjetoWeb.Model.MusicoModel;
import MusicShow.ProjetoWeb.Service.MusicoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/musicos")
@Api(value = "Operações relacionadas aos Músicos", tags = "Músico")
public class MusicoController {

    private final MusicoService musicoService;

    @Autowired
    public MusicoController(MusicoService musicoService) {
        this.musicoService = musicoService;
    }

    @Operation(
            summary = "cria um músico a partir dos dados inseridos",
            description = "Deleta um músico do banco de dados usando o ID fornecido."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Músico criado com sucesso",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\"nome\":\"string\", \"login\":\"string\", \"senha\":\"string\"}"))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("/criar")
    public ResponseEntity<MusicoModel> criarMusico(
            @ApiParam(value = "Objeto do músico contendo nome, login e senha", required = true) @RequestBody MusicoDto musicoDTO) {
        MusicoModel novoMusico = musicoService.criar(musicoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoMusico);
    }


    @Operation(
            summary = "Deletar um músico pelo ID",
            description = "Deleta um músico do banco de dados usando o ID fornecido."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Músico deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Músico não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarMusico(
            @Parameter(description = "ID do músico a ser deletado", required = true) @PathVariable int id) {
        boolean isDeleted = musicoService.deletar(id);
        if (isDeleted) {
            return ResponseEntity.ok("Músico deletado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Músico não encontrado.");
        }
    }

    @Operation(
            summary = "Atualizar as informações de um músico",
            description = "Atualiza os dados do músico usando o ID fornecido e as novas informações do músico."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Músico atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Músico não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarMusico(
            @Parameter(description = "ID do músico a ser atualizado", required = true) @PathVariable int id,
            @Parameter(description = "Objeto do músico com as novas informações", required = true) @RequestBody MusicoModel musico) {
        boolean isUpdated = musicoService.atualizar(id, musico);
        if (isUpdated) {
            return ResponseEntity.ok("Músico atualizado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Músico não encontrado.");
        }
    }

    @Operation(
            summary = "Listar todos os músicos",
            description = "Retorna uma lista de todos os músicos cadastrados no sistema."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de músicos retornada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MusicoModel.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum músico encontrado")
    })
    @GetMapping
    public ResponseEntity<List<ListaMusicosDto>> listarMusicos() {
        List<ListaMusicosDto> musicos = musicoService.listar();
        return ResponseEntity.ok(musicos);
    }

    @Operation(
            summary = "Buscar um músico pelo ID",
            description = "Retorna um músico específico com base no ID fornecido."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Músico encontrado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ListaMusicosDto.class))),
            @ApiResponse(responseCode = "404", description = "Músico não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ListaMusicosDto> buscarMusicoPorId(
            @Parameter(description = "ID do músico", required = true) @PathVariable int id) {
        // Obtendo o MusicoModel do serviço
        MusicoModel musico = musicoService.buscarPorId(id);

        // Convertendo MusicoModel para ListaMusicosDto
        ListaMusicosDto dto = new ListaMusicosDto();
        dto.setNomeMusico(musico.getNome());

        // Retornando o DTO
        return ResponseEntity.ok(dto);
    }
}