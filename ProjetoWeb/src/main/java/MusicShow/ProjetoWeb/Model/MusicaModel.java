package MusicShow.ProjetoWeb.Model;

import jakarta.persistence.*;


@Entity
@Table(name = "musica")
public class MusicaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "music_nr_id")
    private int id;

    @Column(name = "music_tx_titulo")
    private String titulo;

    @Column(name = "music_tx_arquivo")
    private String arquivo;

    @Column(name = "nome_arquivo")
    private String nomeArquivo;

    public MusicaModel(String titulo, String arquivo, String nomeArquivo) {
        this.arquivo = arquivo;
        this.titulo = titulo;
        this.nomeArquivo = nomeArquivo;
    }

    public MusicaModel() {
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }
}