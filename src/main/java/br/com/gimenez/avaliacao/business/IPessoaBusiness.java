package br.com.gimenez.avaliacao.business;

import br.com.gimenez.avaliacao.model.Pessoa;
import br.com.gimenez.avaliacao.model.PessoaFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IPessoaBusiness {
    ResponseEntity<Pessoa> salvar(Pessoa obj) throws Exception;
    ResponseEntity<Pessoa> alterar(Pessoa obj, long id) throws Exception;
    ResponseEntity<?> excluir(long id);
    ResponseEntity<Pessoa> buscarPorId(long id);
    ResponseEntity<List<Pessoa>> buscarTodos();
    Page<Pessoa> pesquisar(PessoaFiltro filtro, Pageable pageable);
}
