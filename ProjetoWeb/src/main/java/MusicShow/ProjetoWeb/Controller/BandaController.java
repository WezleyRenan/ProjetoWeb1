package MusicShow.ProjetoWeb.Controller;

import MusicShow.ProjetoWeb.Dto.*;
import MusicShow.ProjetoWeb.Model.BandaModel;
import MusicShow.ProjetoWeb.Service.BandaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/bandas")
public class BandaController {


    private BandaService bandaService;

    public BandaController(BandaService bandaService) {
        this.bandaService = bandaService;
    }

    @Operation(
            summary = "Criar uma banda",
            description = "Cria uma banda associada ao representante e salva no sistema.",
            operationId = "criarBanda"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Banda criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados da banda")
    })
    @PostMapping("/criar")
    public ResponseEntity<BandaModel> criarBanda(
            @Parameter(description = "Nome da banda") @RequestParam String nome,
            @Parameter(description = "ID do representante") @RequestParam Integer representanteId) {

        BandaDtoRepresentante bandaDto = new BandaDtoRepresentante();
        bandaDto.setNome(nome);
        bandaDto.setRepresentanteId(representanteId);

        BandaModel bandaCriada = bandaService.criarBanda(bandaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(bandaCriada);
    }

    @PostMapping("/adicionarMembro")
    @Operation(
            summary = "Adicionar membro à banda",
            description = "Adiciona membros para uma banda, ambos devem estar inseridos no banco de dados. Apenas o representante pode adicionar membros.",
            operationId = "adicionarMembro"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Membro adicionado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao validar ou adicionar membro")
    })
    public ResponseEntity<String> adicionarMembro(
            @RequestParam
            @Parameter(description = "ID da banda", example = "1", required = true) Integer bandaId,
            @RequestParam
            @Parameter(description = "ID do músico a ser adicionado", example = "2", required = true) Integer musicoId,
            @RequestParam
            @Parameter(description = "ID do representante da banda", example = "3", required = true) Integer representante) {
        try {
            // Validação do representante
            bandaService.validarRepresentante(bandaId, representante);

            // Adicionando o membro
            String resposta = bandaService.adicionarMembro(new AdicionarMembroDto(bandaId, musicoId, representante));
            return ResponseEntity.ok(resposta);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/listarMembros/{bandaId}")
    @Operation(
            summary = "Listar membros de uma banda",
            description = "Retorna a lista de membros de uma banda específica.",
            operationId = "listarMembros"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de membros retornada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao listar membros")
    })
    public ResponseEntity<List<ListaMusicosDto>> listarMembrosPorBanda(@PathVariable Integer bandaId) {
        try {
            List<ListaMusicosDto> membros = bandaService.listarMembrosPorBanda(bandaId);
            return ResponseEntity.ok(membros);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }
    }
}