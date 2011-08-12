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

import br.com.artefino.ordermanager.server.entities.Cliente;
import br.com.artefino.ordermanager.server.entities.Pedido;
import br.com.artefino.ordermanager.server.entities.SituacaoPedido;
import br.com.artefino.ordermanager.server.util.JPAUtil;

public class PedidoBO {

	public static List<Pedido> pesquisarPedido(Map<String, Object> parametros) {
		EntityManager em = JPAUtil.getEntityManager();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder
				.createQuery(Pedido.class);
		Root<Pedido> root = criteriaQuery.from(Pedido.class);
		criteriaQuery.select(root);

		List<Predicate> predicates = new ArrayList<Predicate>();
		if (parametros != null) {
			if (parametros.containsKey("idCliente")
					&& parametros.get("idCliente") != null) {
				Join<Pedido, Cliente> cliente = root.join("cliente");
				predicates.add(criteriaBuilder.equal(cliente.get("id").as(
						Long.class), (Long) parametros.get("idCliente")));
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
			if (parametros.containsKey("situacao")
					&& parametros.get("situacao") != null) {
				Join<Pedido, SituacaoPedido> situacao = root
						.join("situacaoPedido");
				predicates.add(criteriaBuilder.equal(situacao.get("id").as(
						Long.class), (Long) parametros.get("situacao")));
			}

		}

		if (predicates.size() > 0) {
			criteriaQuery.where(predicates.toArray(new Predicate[predicates
					.size()]));
		}

		TypedQuery<Pedido> typedQuery = em.createQuery(criteriaQuery);
		List<Pedido> pedidos = typedQuery.getResultList();

		return pedidos;

	}
}
