package MusicShow.ProjetoWeb.Service;

import MusicShow.ProjetoWeb.Dto.AdicionarMembroDto;
import MusicShow.ProjetoWeb.Dto.BandaDtoRepresentante;
import MusicShow.ProjetoWeb.Dto.ListaMusicosDto;
import MusicShow.ProjetoWeb.Model.BandaModel;
import MusicShow.ProjetoWeb.Model.MusicoBandaModel;
import MusicShow.ProjetoWeb.Model.MusicoModel;
import MusicShow.ProjetoWeb.Repository.BandaRepository;
import MusicShow.ProjetoWeb.Repository.MusicoBandaRepository;
import MusicShow.ProjetoWeb.Repository.MusicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BandaService {

    @Autowired
    private BandaRepository bandaRepository;

    @Autowired
    private MusicoRepository musicoRepository;

    @Autowired
    private MusicoBandaRepository musicoBandaRepository;


    public BandaModel criarBanda(BandaDtoRepresentante bandaDto) {
        // Verificar se o representante foi passado corretamente
        if (bandaDto.getRepresentanteId() == null) {
            throw new IllegalArgumentException("O ID do representante é obrigatório.");
        }

        // Buscar o representante pelo ID
        Optional<MusicoModel> representanteOpt = musicoRepository.findById(bandaDto.getRepresentanteId());
        if (!representanteOpt.isPresent()) {
            throw new IllegalArgumentException("Representante não encontrado com o ID: " + bandaDto.getRepresentanteId());
        }
        MusicoModel representante = representanteOpt.get();

        // Criar a banda
        BandaModel banda = new BandaModel(bandaDto.getNome(), representante);

        // Salvar a banda no banco de dados
        return bandaRepository.save(banda);
    }
    // Validação do representante
    public void validarRepresentante(Integer bandaId, Integer representanteId) {
        BandaModel banda = bandaRepository.findById(bandaId)
                .orElseThrow(() -> new IllegalArgumentException("Banda não encontrada."));

        MusicoModel representante = musicoRepository.findById(representanteId)
                .orElseThrow(() -> new IllegalArgumentException("Representante não encontrado."));

        if (!representante.equals(banda.getRepresentante())) {
            throw new IllegalArgumentException("Somente o representante da banda pode adicionar membros.");
        }
    }

    // Adiciona um membro à banda
    public String adicionarMembro(AdicionarMembroDto adicionarMembroDto) {
        BandaModel banda = bandaRepository.findById(adicionarMembroDto.getBandaId()).orElseThrow(() -> new IllegalArgumentException("Banda não encontrada."));

        MusicoModel musico = musicoRepository.findById(adicionarMembroDto.getMusicoId()).orElseThrow(() -> new IllegalArgumentException("Músico não encontrado."));

        if (adicionarMembroDto.getMusicoId() == adicionarMembroDto.getRepresentante()){
            throw new IllegalArgumentException("nao é possivel adicionar pois ele é o representante da banda");
        }
        MusicoBandaModel musicoBanda = new MusicoBandaModel(musico, banda);
        musicoBandaRepository.save(musicoBanda);

        return "Membro adicionado com sucesso.";
    }
    public List<ListaMusicosDto> listarMembrosPorBanda(Integer bandaId) {
        // Verifica se a banda existe
        BandaModel banda = bandaRepository.findById(bandaId)
                .orElseThrow(() -> new IllegalArgumentException("Banda não encontrada."));

        // Busca os membros da banda
        List<MusicoBandaModel> membros = musicoBandaRepository.findByBanda(banda);

        // Mapeia os resultados para a classe ListaMusicosDto
        return membros.stream()
                .map(musicoBanda -> {
                    ListaMusicosDto dto = new ListaMusicosDto();
                    dto.setNomeMusico(musicoBanda.getMusico().getNome());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
