package MusicShow.ProjetoWeb.Repository;

import MusicShow.ProjetoWeb.Model.MusicaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MusicaRepository extends JpaRepository<MusicaModel,Integer> {

    Optional<MusicaModel> findByTitulo(String titulo);

}
