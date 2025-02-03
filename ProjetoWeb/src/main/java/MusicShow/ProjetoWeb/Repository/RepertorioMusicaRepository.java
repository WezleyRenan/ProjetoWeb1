package MusicShow.ProjetoWeb.Repository;

import MusicShow.ProjetoWeb.Model.MusicaModel;
import MusicShow.ProjetoWeb.Model.RepertorioModel;
import MusicShow.ProjetoWeb.Model.RepertorioMusicaModel;
import MusicShow.ProjetoWeb.Model.StatusRepertorio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RepertorioMusicaRepository extends JpaRepository<RepertorioMusicaModel, Integer> {

    List<RepertorioMusicaModel> findByRepertorio(RepertorioModel repertorio);



    // Encontrar uma RepertorioMusicaModel pelo id do repertório e id da música
    RepertorioMusicaModel findByRepertorioIdAndMusicaId(int repertorioId, int musicaId);
    Optional<RepertorioMusicaModel> findByRepertorioAndMusica(RepertorioModel repertorio, MusicaModel musica);
    List<RepertorioMusicaModel> findByRepertorioAndStatus(RepertorioModel repertorio, StatusRepertorio status);

}
