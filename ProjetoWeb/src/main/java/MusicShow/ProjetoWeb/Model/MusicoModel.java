package MusicShow.ProjetoWeb.Model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "musico")
public class MusicoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mus_nr_id")
    private int id;

    @Column(name = "mus_tx_nome")
    private String nome;

    @Column(name = "mus_tx_login")
    private String login;

    @Column(name = "mus_tx_senha")
    private String senha;


    @ManyToMany
    @JoinTable(
            name = "musico_banda",
            joinColumns = @JoinColumn(name = "mus_nr_id"), // Coluna que referencia o músico
            inverseJoinColumns = @JoinColumn(name = "ban_nr_id") // Coluna que referencia a banda
    )
    private List<BandaModel> bandas;

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<BandaModel> getBandas() {
        return bandas;
    }

    public void setBandas(List<BandaModel> bandas) {
        this.bandas = bandas;
    }
}


