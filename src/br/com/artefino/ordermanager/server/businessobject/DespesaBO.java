package br.com.artefino.ordermanager.server.businessobject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.artefino.ordermanager.server.entities.CategoriaDespesa;
import br.com.artefino.ordermanager.server.entities.Despesa;
import br.com.artefino.ordermanager.server.util.JPAUtil;

public class DespesaBO {

	public static List<Despesa> pesquisarDespesas(
			Map<String, Object> parametros, int maxResults, int firstResult) {
		EntityManager em = JPAUtil.getEntityManager();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Despesa> criteriaQuery = criteriaBuilder
				.createQuery(Despesa.class);
		Root<Despesa> root = criteriaQuery.from(Despesa.class);
		criteriaQuery.select(root);

		List<Predicate> predicates = new ArrayList<Predicate>();
		if (parametros != null) {
			if (parametros.containsKey("idCategoria")
					&& parametros.get("idCategoria") != null) {
				Join<Despesa, CategoriaDespesa> categoria = root
						.join("categoria");
				predicates.add(criteriaBuilder.equal(categoria.get("id").as(
						Long.class), (Long) parametros.get("idCategoria")));
			}
			if (parametros.containsKey("dataInicial")
					&& parametros.get("dataInicial") != null) {
				predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(
						"dataCadastro").as(Date.class), new Date(
						(Long) parametros.get("dataInicial"))));
			}

			if (parametros.containsKey("dataFinal")
					&& parametros.get("dataFinal") != null) {
				predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(
						"dataCadastro").as(Date.class), new Date(
						(Long) parametros.get("dataFinal"))));
			}

		}

		if (predicates.size() > 0) {
			criteriaQuery.where(predicates.toArray(new Predicate[predicates
					.size()]));
		}

		TypedQuery<Despesa> typedQuery = em.createQuery(criteriaQuery);
		if (maxResults != 0) {
			typedQuery.setMaxResults(maxResults);
			typedQuery.setFirstResult(firstResult);
		}

		List<Despesa> despesas = typedQuery.getResultList();

		return despesas;

	}

	public static List<Despesa> pesquisarDespesas(Map<String, Object> parametros) {
		return pesquisarDespesas(parametros, 0, 0);

	}

	public static Long retornarTotalDespesas(Map<String, Object> parametros) {

		EntityManager em = JPAUtil.getEntityManager();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

		CriteriaQuery<Long> criteriaQuery = criteriaBuilder
				.createQuery(Long.class);
		Root<Despesa> root = criteriaQuery.from(Despesa.class);
		criteriaQuery.select(criteriaBuilder.count(root));

		List<Predicate> predicates = new ArrayList<Predicate>();
		if (parametros != null) {
			if (parametros.containsKey("idCategoria")
					&& parametros.get("idCategoria") != null) {
				Join<Despesa, CategoriaDespesa> categoria = root
						.join("categoria");
				predicates.add(criteriaBuilder.equal(categoria.get("id").as(
						Long.class), (Long) parametros.get("idCategoria")));
			}
			if (parametros.containsKey("dataInicial")
					&& parametros.get("dataInicial") != null) {
				predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(
						"dataCadastro").as(Date.class), new Date(
						(Long) parametros.get("dataInicial"))));
			}

			if (parametros.containsKey("dataFinal")
					&& parametros.get("dataFinal") != null) {
				predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(
						"dataCadastro").as(Date.class), new Date(
						(Long) parametros.get("dataFinal"))));
			}

		}

		if (predicates.size() > 0) {
			criteriaQuery.where(predicates.toArray(new Predicate[predicates
					.size()]));
		}

		TypedQuery<Long> typedQuery = em.createQuery(criteriaQuery);
		return typedQuery.getSingleResult();

	}
}
