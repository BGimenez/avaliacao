package br.com.gimenez.avaliacao.model;

import br.com.gimenez.avaliacao.uteis.Validadores;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Entity
@Table(name = "pessoa")
public class Pessoa extends BaseEntidade {

    @NotBlank(message = "O campo nome é obrigatório.")
    @Column(name = "nome")
    private String nome;

    @NotBlank(message = "O campo CPF é obrigatório.")
    @Column(name = "cpf")
    private String cpf;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "O campo Data de Nascimento é obrigatório.")
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pessoa_id")
    private List<Contato> contatos;

    public Pessoa() {
    }

    public Pessoa(@NotBlank(message = "O campo nome é obrigatório.") String nome, @NotBlank(message = "O campo CPF é obrigatório.") String cpf, @NotNull(message = "O campo Data de Nascimento é obrigatório.") LocalDate dataNascimento, List<Contato> contatos) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.contatos = contatos;
    }

    public void validar() throws Exception {
        if ( this.getContatos() == null || this.getContatos().size() <= 0 )
            throw new Exception("É obrigatório informar ao menos um contato.");

        if ( Validadores.validarCpf(this.getCpf()) )
            throw new Exception("O CPF informado não é válido. Por favor, verifique!");

        if ( this.dataNascimento.isAfter(LocalDate.now()) )
            throw new Exception("Não é permitido informar Data de Nascimento futura. Por favor, verifique!");
    }
}
