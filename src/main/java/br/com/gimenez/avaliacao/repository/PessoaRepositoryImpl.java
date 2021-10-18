package br.com.gimenez.avaliacao.repository;

import br.com.gimenez.avaliacao.model.Pessoa;
import br.com.gimenez.avaliacao.model.PessoaFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class PessoaRepositoryImpl implements PessoaRepositoryCustom {

    @PersistenceContext
    protected EntityManager _entityManager;

    public Page<Pessoa> pesquisar(PessoaFiltro filtro, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = this._entityManager.getCriteriaBuilder();
        CriteriaQuery<Pessoa> criteriaQuery = criteriaBuilder.createQuery(Pessoa.class);
        Root<Pessoa> root = criteriaQuery.from(Pessoa.class);

        List<Predicate> predicates = new ArrayList<>();

        if ( filtro.getId() != null && filtro.getId() > 0 )
            predicates.add(criteriaBuilder.equal(root.get("id"), filtro.getId()));

        if ( !StringUtils.isEmpty(filtro.getNome()) )
            predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("nome")), "%" + filtro.getNome().toUpperCase() + "%"));

        if ( !StringUtils.isEmpty(filtro.getCpf()) )
            predicates.add(criteriaBuilder.equal(root.get("cpf"), filtro.getCpf()));

        Predicate[] filtros = predicates.toArray(new Predicate[predicates.size()]);

        criteriaQuery.where(filtros).orderBy(criteriaBuilder.asc(root.get("id")));


        TypedQuery<Pessoa> typedQuery = this._entityManager.createQuery(criteriaQuery);

        typedQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        typedQuery.setMaxResults(pageable.getPageSize());

        List<Pessoa> result = typedQuery.getResultList();

        return new PageImpl<>(result, pageable, getTotalRegistros(criteriaBuilder, root, filtros));
    }

    protected Long getTotalRegistros(CriteriaBuilder criteriaBuilder, Root root, Predicate[] predicates) {
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        root = criteriaQuery.from(Pessoa.class);

        criteriaQuery.select(criteriaBuilder.count(root)).where(predicates);
        return this._entityManager.createQuery(criteriaQuery).getSingleResult();
    }
}
