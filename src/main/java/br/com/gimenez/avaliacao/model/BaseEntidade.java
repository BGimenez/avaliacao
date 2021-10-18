package br.com.gimenez.avaliacao.model;

import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Classe abstrata que contém os campos essênciais para qualquer entidade do sistema.
 */
@Getter
@MappedSuperclass
public abstract class BaseEntidade implements Serializable {

    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntidade that = (BaseEntidade) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
