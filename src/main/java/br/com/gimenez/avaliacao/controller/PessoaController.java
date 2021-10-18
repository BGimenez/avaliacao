package br.com.gimenez.avaliacao.controller;

import br.com.gimenez.avaliacao.business.IPessoaBusiness;
import br.com.gimenez.avaliacao.model.Pessoa;
import br.com.gimenez.avaliacao.model.PessoaFiltro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Classe de controller utilizada para implementar os recursos de end-points.
 */
@RestController
@RequestMapping(value = "/pessoa")
public class PessoaController {

    @Autowired
    private IPessoaBusiness _pessoaBusiness;

    @PostMapping
    public ResponseEntity<Pessoa> salvar(@Valid @RequestBody Pessoa obj) throws Exception {
        return this._pessoaBusiness.salvar(obj);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Pessoa> alterar(@Valid @RequestBody Pessoa obj, @PathVariable long id) throws Exception {
        return this._pessoaBusiness.alterar(obj, id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> excluir(@PathVariable long id) {
        return this._pessoaBusiness.excluir(id);
    }

    @GetMapping(value = "/buscarTodos")
    public ResponseEntity<List<Pessoa>> buscarTodos() {
        return this._pessoaBusiness.buscarTodos();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pessoa> buscarPorId(@PathVariable long id) {
        return this._pessoaBusiness.buscarPorId(id);
    }

    @GetMapping(value = "/pesquisar")
    public Page<Pessoa> pesquisar(PessoaFiltro filtro, Pageable pageable) {
        return this._pessoaBusiness.pesquisar(filtro, pageable);
    }
}
