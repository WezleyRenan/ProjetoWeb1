package MusicShow.ProjetoWeb.Model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "banda")
public class BandaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ban_nr_id")
    private Integer id;

    @Column(name = "ban_tx_nome")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "mus_nr_representante")
    private MusicoModel representante;

    @ManyToMany(mappedBy = "bandas")
    private List<MusicoModel> membros; // Músicos que fazem parte da banda



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BandaModel() {
    }

    public BandaModel(String nome, MusicoModel representante) {
        this.nome = nome;
        this.representante = representante;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public MusicoModel getRepresentante() {
        return representante;
    }

    public void setRepresentante(MusicoModel representante) {
        this.representante = representante;
    }

    public List<MusicoModel> getMembros() {
        return membros;
    }

    public void setMembros(List<MusicoModel> membros) {
        this.membros = membros;
    }
}
