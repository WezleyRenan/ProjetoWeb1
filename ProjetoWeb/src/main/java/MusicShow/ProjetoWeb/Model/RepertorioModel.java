package MusicShow.ProjetoWeb.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "repertorio")
public class RepertorioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rep_nr_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "ban_nr_id")
    private BandaModel banda;

    public RepertorioModel(BandaModel banda) {
        this.banda = banda;
    }

    public RepertorioModel(){
    }

    public BandaModel getBanda() {
        return banda;
    }

    public void setBanda(BandaModel banda) {
        this.banda = banda;
    }

}
