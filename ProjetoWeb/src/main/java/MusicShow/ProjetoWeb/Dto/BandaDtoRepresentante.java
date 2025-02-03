package MusicShow.ProjetoWeb.Dto;

import java.util.List;

public class BandaDtoRepresentante {

    private String nome;

    private Integer representanteId;

    public BandaDtoRepresentante(String nome, Integer representanteId, List<Integer> membrosIds) {
        this.nome = nome;
        this.representanteId = representanteId;
    }

    public BandaDtoRepresentante() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getRepresentanteId() {
        return representanteId;
    }

    public void setRepresentanteId(Integer representanteId) {
        this.representanteId = representanteId;
    }


}
