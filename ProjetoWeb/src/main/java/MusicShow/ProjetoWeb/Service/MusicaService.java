package MusicShow.ProjetoWeb.Service;

import MusicShow.ProjetoWeb.Model.MusicaModel;
import MusicShow.ProjetoWeb.Repository.MusicaRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import java.util.List;
import java.util.Optional;

@Service
public class MusicaService {

    private final MusicaRepository musicaRepository;

    private static final Logger logger = LoggerFactory.getLogger(MusicaService.class);

    private final String pdfStoragePath = "pdfs/musicas";

    public MusicaService(MusicaRepository musicaRepository) {
        this.musicaRepository = musicaRepository;
    }

    @PostConstruct
    public void init() {
        logger.info("Iniciando verificação e atualização de registros de músicas...");

        addOrUpdateMusica("Bohemian Rhapsody");
        addOrUpdateMusica("Hotel California");
        addOrUpdateMusica("Stairway to Heaven");
        addOrUpdateMusica("Imagine");
        addOrUpdateMusica("Hey Jude");
        addOrUpdateMusica("Smells Like Teen Spirit");
        addOrUpdateMusica("Wonderwall");
        addOrUpdateMusica("Sweet Child O' Mine");
        addOrUpdateMusica("Billie Jean");
        addOrUpdateMusica("Like a Rolling Stone");
        addOrUpdateMusica("Rolling in the Deep");
        addOrUpdateMusica("Someone Like You");
        addOrUpdateMusica("Lose Yourself");
        addOrUpdateMusica("Shape of You");
        addOrUpdateMusica("Blinding Lights");
        addOrUpdateMusica("I Will Always Love You");
        addOrUpdateMusica("My Heart Will Go On");
        addOrUpdateMusica("Shake It Off ");
        addOrUpdateMusica("Uptown Funk");
        addOrUpdateMusica("Let It Be");
        addOrUpdateMusica("Dancing Queen");
        addOrUpdateMusica("ENCARTA '97");
        addOrUpdateMusica("Snake Way");
        addOrUpdateMusica("Messages from the Stars");
        addOrUpdateMusica("Plastic love");
        addOrUpdateMusica("Compass");
        addOrUpdateMusica("I ran");
        addOrUpdateMusica("52nd Street");
        addOrUpdateMusica("Deja vu");
        addOrUpdateMusica("Bad Apple");
    }

    public MusicaModel adicionarMusica(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("O título da música é obrigatório");
        }

        try {
            // Geração do nome e do caminho completo do arquivo
            String nomeArquivo = gerarNomeArquivo(titulo);
            String caminhoCompleto = pdfStoragePath + "/" + nomeArquivo;

            // Criação do diretório, se necessário
            Files.createDirectories(Paths.get(pdfStoragePath));

            // Criação do PDF
            PdfWriter writer = new PdfWriter(caminhoCompleto);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("Música: " + titulo));
            document.close();

            // Criação e salvamento do objeto MusicaModel
            MusicaModel musica = new MusicaModel();
            musica.setTitulo(titulo);
            musica.setArquivo(caminhoCompleto); // Caminho completo do arquivo
            musica.setNomeArquivo(nomeArquivo); // Apenas o nome do arquivo

            return musicaRepository.save(musica);

        } catch (IOException e) {
            throw new RuntimeException("Erro ao criar arquivo PDF para a música", e);
        }
    }

    private void addOrUpdateMusica(String titulo) {
        try {
            // Gera o nome do arquivo e o caminho completo
            String nomeArquivo = gerarNomeArquivo(titulo);
            String caminhoCompleto = pdfStoragePath + "/" + nomeArquivo;

            // Criação do diretório de armazenamento, caso não exista
            Files.createDirectories(Paths.get(pdfStoragePath));

            // Busca no repositório para verificar se a música já existe
            Optional<MusicaModel> optionalMusica = musicaRepository.findByTitulo(titulo);

            MusicaModel musica;

            if (optionalMusica.isEmpty()) {
                // Se a música não existe, cria um novo registro
                musica = new MusicaModel(titulo, caminhoCompleto, nomeArquivo);
                logger.info("Criando nova música: {} com arquivo: {}", titulo, nomeArquivo);
            } else {
                // Se a música existe, atualiza os campos necessários
                musica = optionalMusica.get();
                musica.setArquivo(caminhoCompleto);
                musica.setNomeArquivo(nomeArquivo);
                logger.info("Atualizando música existente: {} com arquivo: {}", titulo, nomeArquivo);
            }

            // Cria o arquivo PDF correspondente
            try (PdfWriter writer = new PdfWriter(caminhoCompleto);
                 PdfDocument pdf = new PdfDocument(writer);
                 Document document = new Document(pdf)) {
                document.add(new Paragraph("Música: " + titulo));
            }

            // Salva a música no repositório
            musicaRepository.save(musica);

        } catch (IOException e) {
            logger.error("Erro ao criar arquivo PDF para a música: {}", titulo, e);
        } catch (Exception e) {
            logger.error("Erro ao adicionar ou atualizar música: {}", titulo, e);
        }
    }

    public MusicaModel saveOrUpdateMusica(MusicaModel musica) {
        try {
            String nomeArquivo = gerarNomeArquivo(musica.getTitulo());
            String caminhoCompleto = pdfStoragePath + "/" + nomeArquivo;

            musica.setArquivo(caminhoCompleto);
            musica.setNomeArquivo(nomeArquivo);

            logger.info("Salvando ou atualizando música: {} com arquivo: {}", musica.getTitulo(), nomeArquivo);
            return musicaRepository.save(musica);
        } catch (Exception e) {
            logger.error("Erro ao salvar ou atualizar a música: {}", musica.getTitulo(), e);
            throw e;
        }
    }

    public List<MusicaModel> listarMusicas() { return musicaRepository.findAll();
    }

    private String gerarNomeArquivo(String titulo) {
        return titulo.trim().replaceAll("[^a-zA-Z0-9]", "_").toLowerCase() + ".pdf";
    }

}