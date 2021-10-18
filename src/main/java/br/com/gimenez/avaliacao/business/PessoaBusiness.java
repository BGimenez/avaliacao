package br.com.gimenez.avaliacao.business;

import br.com.gimenez.avaliacao.model.Pessoa;
import br.com.gimenez.avaliacao.model.PessoaFiltro;
import br.com.gimenez.avaliacao.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("pessoaService")
public class PessoaBusiness implements IPessoaBusiness {

    @Autowired
    private PessoaRepository _pessoaRepository;

    public ResponseEntity<Pessoa> salvar(Pessoa obj) throws Exception {
        obj.validar();
        obj = this._pessoaRepository.save(obj);

        return ResponseEntity.ok().body(obj);
    }

    public ResponseEntity<Pessoa> alterar(Pessoa obj, long pessoaId) throws Exception {
        obj.validar();

        return this._pessoaRepository.findById(pessoaId)
                .map(dados -> {
                    obj.setId(dados.getId());
                    dados = obj;

                    dados = this._pessoaRepository.save(dados);
                    return ResponseEntity.ok().body(dados);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> excluir(long id) {
        return this._pessoaRepository.findById(id)
                .map(dados -> {
                    this._pessoaRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Pessoa> buscarPorId(long id) {
        return this._pessoaRepository.findById(id)
                .map(dados -> ResponseEntity.ok().body(dados))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<List<Pessoa>> buscarTodos() {
        return ResponseEntity.ok().body(this._pessoaRepository.findAll());
    }

    public Page<Pessoa> pesquisar(PessoaFiltro filtro, Pageable pageable) {
        return this._pessoaRepository.pesquisar(filtro, pageable);
    }
}
