package MusicShow.ProjetoWeb.Controller;


import MusicShow.ProjetoWeb.Dto.AdicionarRepertorio;
import MusicShow.ProjetoWeb.Model.MusicaModel;
import MusicShow.ProjetoWeb.Model.RepertorioModel;
import MusicShow.ProjetoWeb.Model.RepertorioMusicaModel;
import MusicShow.ProjetoWeb.Service.RepertorioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repertorio")
public class RepertorioController {


    private RepertorioService repertorioService;

    public RepertorioController(RepertorioService repertorioService) {
        this.repertorioService = repertorioService;
    }

    @PostMapping("/adicionar")
    @Operation(summary = "Adicionar repertório à banda",
            description = "Adiciona um novo repertório à banda especificada. A ordem do repertório será sempre 0.")
    public ResponseEntity<String> adicionarRepertorio(@RequestBody AdicionarRepertorio adicionarRepertorio) {
        // Passa o bandaId do DTO para o serviço
        RepertorioModel novoRepertorio = repertorioService.adicionarRepertorio(adicionarRepertorio.getBandaId());
        return ResponseEntity.ok("repertorio criado com sucesso");
    }


    @DeleteMapping("/remover/{idRepertorio}")
    @Operation(summary = "Remover repertório de uma banda",
            description = "Remove um repertório da banda especificada. O repertório será removido com base no ID informado.")
    public ResponseEntity<String> removerRepertorio(@RequestBody AdicionarRepertorio adicionarRepertorio, @PathVariable int idRepertorio) {
        repertorioService.removerRepertorio(adicionarRepertorio.getBandaId(), idRepertorio);
        return ResponseEntity.ok("Repertório removido com sucesso!");
    }
    @PostMapping("/musica")
    @Operation(
            summary = "Adicionar música ao repertório",
            description = "Adiciona uma música ao repertório, incrementando a ordem das músicas já associadas. " +
                    "Se a música já estiver associada ao repertório, apenas o status será alterado para ATIVADO.",
            parameters = {
                    @Parameter(
                            name = "repertorioId",
                            description = "ID do repertório ao qual a música será adicionada",
                            required = true,
                            example = "1"
                    ),
                    @Parameter(
                            name = "musicaId",
                            description = "ID da música que será adicionada ao repertório",
                            required = true,
                            example = "2"
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Música adicionada com sucesso ao repertório",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Parâmetros inválidos ou erro na validação",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Erro interno ao adicionar a música",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    public ResponseEntity<String> adicionarMusicaAoRepertorio(
            @RequestParam Integer repertorioId,
            @RequestParam Integer musicaId) {

        try {
            // Chama o serviço para adicionar a música ao repertório
            RepertorioMusicaModel repertorioMusica = repertorioService.adicionarMusicaAoRepertorio(repertorioId, musicaId);
            return ResponseEntity.ok("Música adicionada com sucesso!!!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao adicionar música ao repertório: " + e.getMessage());
        }
    }

    @PatchMapping("/musica/desativar")
    @Operation(summary = "Desativar música no repertório",
            description = "Desativa uma música no repertório específico com base nos IDs do repertório e da música.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Status atualizado para DESATIVADO."),
            @ApiResponse(responseCode = "404", description = "Repertório ou música não encontrados."),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.")
    })
    public ResponseEntity<String> desativarRepertorioMusica(
            @RequestParam int idRepertorio,
            @RequestParam int idMusica) {
        repertorioService.desativarRepertorioMusica(idRepertorio, idMusica);
        return ResponseEntity.ok("Música no repertório desativada com sucesso.");
    }

    @GetMapping("/musicas/ativas/{idRepertorio}")
    @Operation(summary = "Listar músicas ativas de um repertório",
            description = "Retorna todas as músicas associadas a um repertório com o status 'ATIVADO'.")
    public ResponseEntity<List<MusicaModel>> listarMusicasAtivas(
            @Parameter(description = "ID do repertório para buscar as músicas ativas.")
            @PathVariable int idRepertorio) {

        // Chama o serviço para listar as músicas ativas do repertório
        List<MusicaModel> musicasAtivas = repertorioService.listarMusicasAtivasPorRepertorio(idRepertorio);

        return ResponseEntity.ok(musicasAtivas);
    }

}
