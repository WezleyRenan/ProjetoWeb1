package MusicShow.ProjetoWeb.Service;

import MusicShow.ProjetoWeb.Dto.ListaMusicosDto;
import MusicShow.ProjetoWeb.Dto.MusicoDto;
import MusicShow.ProjetoWeb.Model.MusicoModel;
import MusicShow.ProjetoWeb.Repository.MusicoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class MusicoService {

    private final MusicoRepository musicoRepository;

    public MusicoService(MusicoRepository musicoRepository) {
        this.musicoRepository = musicoRepository;
    }

    // Método para criar um novo músico a partir do DTO
    public MusicoModel criar(MusicoDto musicoDTO) {
        MusicoModel novoMusico = new MusicoModel();
        novoMusico.setNome(musicoDTO.getNome());
        novoMusico.setLogin(musicoDTO.getLogin());
        novoMusico.setSenha(musicoDTO.getSenha());

        // Salvando o músico no banco de dados
        return musicoRepository.save(novoMusico);
    }

    // Método para deletar um músico pelo ID
    public boolean deletar(int id) {
        Optional<MusicoModel> musicoModel = musicoRepository.findById(id);
        if (musicoModel.isPresent()) {
            musicoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Método para atualizar um músico
    public boolean atualizar(int id, MusicoModel musico) {
        Optional<MusicoModel> musicoModel = musicoRepository.findById(id);
        if (musicoModel.isPresent()) {
            MusicoModel musicoExistente = musicoModel.get();
            musicoExistente.setNome(musico.getNome());
            musicoExistente.setLogin(musico.getLogin());
            musicoExistente.setSenha(musico.getSenha());
            musicoRepository.save(musicoExistente);
            return true;
        }
        return false;
    }

    //metodo para listar musicos
    public List<ListaMusicosDto> listar() {
        return musicoRepository.findAll()
                .stream()
                .map(musico -> {
                    ListaMusicosDto dto = new ListaMusicosDto();
                    dto.setNomeMusico(musico.getNome());
                    dto.setLogin(musico.getLogin());// Preenchendo o campo nomeMusico
                    return dto;
                })
                .collect(Collectors.toList());
    }

    // Método para buscar um músico pelo ID
    public MusicoModel buscarPorId(int id) {
        return musicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Músico não encontrado"));
    }
}
