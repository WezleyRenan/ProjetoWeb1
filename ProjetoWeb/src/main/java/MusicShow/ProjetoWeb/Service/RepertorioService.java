package MusicShow.ProjetoWeb.Service;

import MusicShow.ProjetoWeb.Model.*;
import MusicShow.ProjetoWeb.Repository.BandaRepository;
import MusicShow.ProjetoWeb.Repository.MusicaRepository;
import MusicShow.ProjetoWeb.Repository.RepertorioMusicaRepository;
import MusicShow.ProjetoWeb.Repository.RepertorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RepertorioService {

    @Autowired
    private RepertorioRepository repertorioRepository;

    @Autowired
    private BandaRepository bandaRepository;

    @Autowired
    private MusicaRepository musicaRepository;

    @Autowired
    private RepertorioMusicaRepository repertorioMusicaRepository;

    public RepertorioModel adicionarRepertorio(Integer bandaId) {
        // Verifica se a banda existe
        BandaModel banda = bandaRepository.findById(bandaId).orElseThrow(() -> new RuntimeException("Banda não encontrada"));

        // Cria o novo repertório
        RepertorioModel novoRepertorio = new RepertorioModel(banda);

        // Salva o repertório no banco de dados
        return repertorioRepository.save(novoRepertorio);
    }

    public void removerRepertorio(Integer bandaId, int idRepertorio) {
        // Encontre o repertório pelo ID e o bandaId
        RepertorioModel repertorio = repertorioRepository.findById(idRepertorio)
                .orElseThrow(() -> new RuntimeException("Repertório não encontrado"));

        // Verifique se o repertório pertence à banda
        if (repertorio.getBanda().getId() != bandaId) {
            throw new RuntimeException("Este repertório não pertence à banda especificada");
        }

        // Remover o repertório
        repertorioRepository.delete(repertorio);
    }

    public RepertorioMusicaModel adicionarMusicaAoRepertorio(Integer repertorioId, Integer musicaId) {
        // Verifica se o repertório existe
        RepertorioModel repertorio = repertorioRepository.findById(repertorioId)
                .orElseThrow(() -> new RuntimeException("Repertório não encontrado"));

        // Verifica se a música existe
        MusicaModel musica = musicaRepository.findById(musicaId)
                .orElseThrow(() -> new RuntimeException("Música não encontrada"));

        // Verifica se a música já está associada ao repertório
        Optional<RepertorioMusicaModel> musicaExistente = repertorioMusicaRepository
                .findByRepertorioAndMusica(repertorio, musica);

        if (musicaExistente.isPresent()) {
            // Se a música já está associada, atualiza o status para ATIVADO
            RepertorioMusicaModel repertorioMusica = musicaExistente.get();
            repertorioMusica.setStatus(StatusRepertorio.ATIVADO);
            return repertorioMusicaRepository.save(repertorioMusica);  // Atualiza a música no repertório
        }

        // Se a música não está associada, cria uma nova associação
        List<RepertorioMusicaModel> repertorioMusicas = repertorioMusicaRepository.findByRepertorio(repertorio);

        // Define a ordem da música (baseada na quantidade de músicas já associadas ao repertório)
        int ordem = repertorioMusicas.size(); // A ordem será o número de músicas já associadas
        RepertorioMusicaModel novoRepertorioMusica = new RepertorioMusicaModel(repertorio, musica);
        novoRepertorioMusica.setStatus(StatusRepertorio.ATIVADO);  // Marca como ATIVADO


        // Salva a nova música associada ao repertório
        return repertorioMusicaRepository.save(novoRepertorioMusica);
    }

    public void desativarRepertorioMusica(int idRepertorio, int idMusica) {
        // Busca o repertório pelo ID
        RepertorioModel repertorio = repertorioRepository.findById(idRepertorio)
                .orElseThrow(() -> new RuntimeException("Repertório não encontrado com ID: " + idRepertorio));

        // Busca a música pelo ID
        MusicaModel musica = musicaRepository.findById(idMusica)
                .orElseThrow(() -> new RuntimeException("Música não encontrada com ID: " + idMusica));

        // Busca a relação entre o repertório e a música
        RepertorioMusicaModel repertorioMusica = repertorioMusicaRepository.findByRepertorioAndMusica(repertorio, musica)
                .orElseThrow(() -> new RuntimeException("Associação entre repertório e música não encontrada."));

        // Atualiza o status para DESATIVADO
        repertorioMusica.setStatus(StatusRepertorio.DESATIVADO);
        repertorioMusicaRepository.save(repertorioMusica); // Salva a alteração no banco de dados
    }

    // Método para listar as músicas com status ATIVADO de um repertório específico
    public List<MusicaModel> listarMusicasAtivasPorRepertorio(int idRepertorio) {
        // Busca o repertório pelo ID
        RepertorioModel repertorio = repertorioRepository.findById(idRepertorio)
                .orElseThrow(() -> new RuntimeException("Repertório não encontrado com ID: " + idRepertorio));

        // Busca as músicas com status ATIVADO associadas ao repertório
        List<RepertorioMusicaModel> repertorioMusicaList = repertorioMusicaRepository
                .findByRepertorioAndStatus(repertorio, StatusRepertorio.ATIVADO);

        // Retorna apenas as músicas (Model) a partir da lista de RepertorioMusicaModel
        List<MusicaModel> musicasAtivas = new ArrayList<>();
        for (RepertorioMusicaModel repertorioMusica : repertorioMusicaList) {
            musicasAtivas.add(repertorioMusica.getMusica());
        }

        return musicasAtivas;
    }


}