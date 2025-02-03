package MusicShow.ProjetoWeb.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "musico_banda")
public class MusicoBandaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Musban_nr_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mus_nr_id")
    private MusicoModel musico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ban_nr_id")
    private BandaModel banda;


    public MusicoBandaModel(MusicoModel musico, BandaModel banda) {
        this.musico = musico;
        this.banda = banda;
    }

    public MusicoBandaModel(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MusicoModel getMusico() {
        return musico;
    }

    public void setMusico(MusicoModel musico) {
        this.musico = musico;
    }

    public BandaModel getBanda() {
        return banda;
    }

    public void setBanda(BandaModel banda) {
        this.banda = banda;
    }
}
