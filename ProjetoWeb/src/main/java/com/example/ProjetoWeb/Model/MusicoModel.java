package com.example.ProjetoWeb.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name = "mus_en_cargo")
    private boolean cargo;


}
