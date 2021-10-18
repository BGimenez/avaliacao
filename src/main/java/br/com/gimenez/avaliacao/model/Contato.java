package br.com.gimenez.avaliacao.model;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Entity
@Table(name = "contato")
public class Contato extends BaseEntidade {

    @NotBlank(message = "O campo nome é obrigatório.")
    @Column(name = "nome")
    private String nome;

    @NotBlank(message = "O campo telefone é obrigatório.")
    @Column(name = "telefone")
    private String telefone;

    @Email
    @Column(name = "email")
    private String email;

    public Contato() {
    }

    public Contato(@NotBlank(message = "O campo nome é obrigatório.") String nome, @NotBlank(message = "O campo telefone é obrigatório.") String telefone, @Email String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }
}
