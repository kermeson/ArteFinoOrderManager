package br.com.artefino.ordermanager.server.businessobject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.artefino.ordermanager.server.entities.Cliente;
import br.com.artefino.ordermanager.server.util.JPAUtil;

public class ClienteBO {

	public static List<Cliente> pesquisarClientes(Map<String, Object> parametros) {
		EntityManager em = JPAUtil.getEntityManager();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Cliente> criteriaQuery = criteriaBuilder
				.createQuery(Cliente.class);
		Root<Cliente> root = criteriaQuery.from(Cliente.class);
		criteriaQuery.select(root);

		List<Predicate> predicates = new ArrayList<Predicate>();
		if (parametros != null) {
			if (parametros.containsKey("nome")
					&& parametros.get("nome") != null) {

				predicates.add(criteriaBuilder.like(root.get("nome").as(
						String.class), "%" + parametros.get("nome") + "%"));
			}
			if (parametros.containsKey("tipoPessoa")
					&& parametros.get("tipoPessoa") != null) {
				predicates.add(criteriaBuilder.equal(root.get(
						"tipoPessoa").as(Integer.class),
						(Integer) parametros.get("tipoPessoa")));
			}
			if (parametros.containsKey("cnpjf")
					&& parametros.get("cnpjf") != null) {
				predicates.add(criteriaBuilder.equal(root.get(
						"cnpjf").as(Long.class),
						(Long) parametros.get("cnpjf")));
			}


		}

		if (predicates.size() > 0) {
			criteriaQuery.where(predicates.toArray(new Predicate[predicates
					.size()]));
		}

		TypedQuery<Cliente> typedQuery = em.createQuery(criteriaQuery);
		List<Cliente> pedidos = typedQuery.getResultList();

		return pedidos;

	}
}
