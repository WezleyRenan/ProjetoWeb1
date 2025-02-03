package MusicShow.ProjetoWeb.Dto;

public class AdicionarMusicaRepertorioDto {

    private Integer repertorioId;
    private Integer musicaId;

    public Integer getRepertorioId() {
        return repertorioId;
    }

    public void setRepertorioId(Integer repertorioId) {
        this.repertorioId = repertorioId;
    }

    public Integer getMusicaId() {
        return musicaId;
    }

    public void setMusicaId(Integer musicaId) {
        this.musicaId = musicaId;
    }
}