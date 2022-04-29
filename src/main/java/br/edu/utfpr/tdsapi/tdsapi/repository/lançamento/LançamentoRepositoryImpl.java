package br.edu.utfpr.tdsapi.tdsapi.repository.lançamento;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import br.edu.utfpr.tdsapi.tdsapi.model.Lançamento;
import br.edu.utfpr.tdsapi.tdsapi.repository.filter.LançamentoFilter;

public class LançamentoRepositoryImpl implements LançamentoRepositoryQuery<Lançamento>{

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Lançamento> filtrar(LançamentoFilter lançamentoFilter, Pageable pageable) {
        
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Lançamento> criteria = builder.createQuery(Lançamento.class);
        Root<Lançamento> root = criteria.from(Lançamento.class);

        Predicate[] predicates = criarRestriçoes(lançamentoFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<Lançamento> query= manager.createQuery(criteria);
        
        adicionarRestriçoesPaginaçao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(lançamentoFilter))
    }

    private Predicate[] criarRestriçoes(LançamentoFilter lançamentoFilter, CriteriaBuilder builder, Root<Lançamento> root){
        
        List<Predicate> predicates = new ArrayList<>();
        
        if(!StringUtils.hasText(lançamentoFilter.getDescricao())){
            predicates.add(builder.like(
                builder.lower(root. get("descrição")), "%" + lançamentoFilter.getDescricao().toLowerCase() + "%"));
        }

        if(lançamentoFilter.getDataVencimentoDe() != null){
            predicates.add(
                builder.greaterThanOrEqualTo(root.get("dataVencimento"), lançamentoFilter.getDataVencimentoDe()));
        }

        if(lançamentoFilter.getDataVencimentoAte() != null){
            predicates.add(
                builder.greaterThanOrEqualTo(root.get("dataVencimento"), lançamentoFilter.getDataVencimentoAte()));
        }


        return predicates.toArray(new Predicate[predicates.size()]);
    }

    private Long total(LançamentoFilter lançamentoFilter) {
        
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Lançamento> root = criteria.from(Lançamento.class);

        Predicate[] predicates = criarRestriçoes(lançamentoFilter, builder, root);
        criteria.where(predicates);

        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();

    }

    private void adicionarRestriçoesPaginaçao(TypedQuery<Lançamento> query, Pageable pageable) {
        
        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

        query.setFirstResult(primeiroRegistroDaPagina);
        query.setMaxResults(totalRegistrosPorPagina);
    }


}
