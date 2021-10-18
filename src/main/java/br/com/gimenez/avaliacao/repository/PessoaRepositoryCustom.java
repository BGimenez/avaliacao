package br.com.gimenez.avaliacao.repository;

import br.com.gimenez.avaliacao.model.Pessoa;
import br.com.gimenez.avaliacao.model.PessoaFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PessoaRepositoryCustom {
    Page<Pessoa> pesquisar(PessoaFiltro filtro, Pageable pageable);
}
