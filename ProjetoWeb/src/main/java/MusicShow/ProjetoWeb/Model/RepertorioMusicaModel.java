package MusicShow.ProjetoWeb.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "repertorio_musica")
public class RepertorioMusicaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rep_mus_nr_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "rep_nr_id")
    private RepertorioModel repertorio;

    @ManyToOne
    @JoinColumn(name = "music_nr_id")
    private MusicaModel musica;

    @Enumerated(EnumType.STRING)
    @Column(name = "rep_mus_status")
    private StatusRepertorio status = StatusRepertorio.ATIVADO;

    public RepertorioMusicaModel(RepertorioModel repertorio, MusicaModel musica) {
        this.repertorio = repertorio;
        this.musica = musica;
    }

    public RepertorioMusicaModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RepertorioModel getRepertorio() {
        return repertorio;
    }

    public void setRepertorio(RepertorioModel repertorio) {
        this.repertorio = repertorio;
    }

    public MusicaModel getMusica() {
        return musica;
    }

    public void setMusica(MusicaModel musica) {
        this.musica = musica;
    }

    public StatusRepertorio getStatus() {
        return status;
    }

    public void setStatus(StatusRepertorio status) {
        this.status = status;
    }

}
