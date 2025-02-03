package MusicShow.ProjetoWeb.Dto;

public class AdicionarMembroDto {

    private Integer bandaId;
    private Integer musicoId;
    private Integer representante;

    public AdicionarMembroDto(Integer bandaId, Integer musicoId, Integer representante) {
        this.bandaId = bandaId;
        this.musicoId = musicoId;
        this.representante = representante;
    }

    public AdicionarMembroDto() {}

    public Integer getBandaId() {
        return bandaId;
    }

    public void setBandaId(Integer bandaId) {
        this.bandaId = bandaId;
    }

    public Integer getMusicoId() {
        return musicoId;
    }

    public void setMusicoId(Integer musicoId) {
        this.musicoId = musicoId;
    }

    public Integer getRepresentante() {
        return representante;
    }

    public void setRepresentante(Integer representante) {
        this.representante = representante;
    }
}
