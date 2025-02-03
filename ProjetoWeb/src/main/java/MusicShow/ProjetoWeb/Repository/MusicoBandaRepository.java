package MusicShow.ProjetoWeb.Repository;

import MusicShow.ProjetoWeb.Model.BandaModel;
import MusicShow.ProjetoWeb.Model.MusicoBandaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MusicoBandaRepository extends JpaRepository<MusicoBandaModel,Integer> {
    List<MusicoBandaModel> findByBanda(BandaModel banda);

}
