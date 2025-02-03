package MusicShow.ProjetoWeb.Controller;

import MusicShow.ProjetoWeb.Model.MusicaModel;
import MusicShow.ProjetoWeb.Service.MusicaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@RestController
@RequestMapping("/api/musicas")
public class MusicaController {

    private final MusicaService musicaService;

    public MusicaController(MusicaService musicaService) {
        this.musicaService = musicaService;
    }

    @Operation(summary = "Adicionar uma nova música", description = "Adiciona uma nova música ao banco de dados com o título informado. O nome do arquivo é gerado automaticamente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Música adicionada com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MusicaModel.class))}),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content)
    })
    @PostMapping("/adicionar")
    public ResponseEntity<MusicaModel> adicionarMusica(@RequestParam String titulo) {
        try {
            MusicaModel musicaAdicionada = musicaService.adicionarMusica(titulo);
            return ResponseEntity.ok(musicaAdicionada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping
    @Operation(summary = "Listar todas as músicas", description = "Retorna uma lista de todas as músicas.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de músicas retornada com sucesso",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Erro ao listar músicas")
    })
    public ResponseEntity<List<MusicaModel>> listarMusicas() {
        List<MusicaModel> musicas = musicaService.listarMusicas();
        return ResponseEntity.ok(musicas);
    }
}
