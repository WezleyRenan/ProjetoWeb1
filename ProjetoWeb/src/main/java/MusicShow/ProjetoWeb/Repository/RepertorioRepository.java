package MusicShow.ProjetoWeb.Repository;

import MusicShow.ProjetoWeb.Model.RepertorioModel;
import MusicShow.ProjetoWeb.Model.BandaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepertorioRepository extends JpaRepository<RepertorioModel, Integer> {

}

