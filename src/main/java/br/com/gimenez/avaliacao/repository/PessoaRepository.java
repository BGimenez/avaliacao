package br.com.gimenez.avaliacao.repository;

import br.com.gimenez.avaliacao.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>, PessoaRepositoryCustom {
}
