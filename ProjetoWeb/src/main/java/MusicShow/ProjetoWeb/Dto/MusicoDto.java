package MusicShow.ProjetoWeb.Dto;

import MusicShow.ProjetoWeb.Model.MusicoModel;
import io.swagger.v3.oas.annotations.media.Schema;

public class MusicoDto {

    @Schema(description = "Nome do músico", example = "Jhon")
    private String nome;

    @Schema(description = "Login do músico", example = "jhon")
    private String login;

    @Schema(description = "Senha do músico", example = "159357")
    private String senha;

    // Construtores, getters e setters
    public MusicoDto() {
    }

    public MusicoDto(String nome, String login, String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
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
}