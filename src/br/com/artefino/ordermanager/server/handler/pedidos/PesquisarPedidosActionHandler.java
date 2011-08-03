package br.com.artefino.ordermanager.server.handler.pedidos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.artefino.ordermanager.server.entities.Pedido;
import br.com.artefino.ordermanager.server.util.JPAUtil;
import br.com.artefino.ordermanager.shared.action.pedidos.PesquisarPedidosAction;
import br.com.artefino.ordermanager.shared.action.pedidos.PesquisarPedidosResult;
import br.com.artefino.ordermanager.shared.vo.PedidoVo;

import com.allen_sauer.gwt.log.client.Log;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class PesquisarPedidosActionHandler implements
		ActionHandler<PesquisarPedidosAction, PesquisarPedidosResult> {

	@Inject
	public PesquisarPedidosActionHandler() {
	}

	@Override
	public PesquisarPedidosResult execute(PesquisarPedidosAction action,
			ExecutionContext context) throws ActionException {

		PesquisarPedidosResult result = null;

		try {

			EntityManager em = JPAUtil.getEntityManager();
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder
					.createQuery(Pedido.class);
			Root<Pedido> root = criteriaQuery.from(Pedido.class);
			criteriaQuery.select(root);
			TypedQuery<Pedido> typedQuery = em.createQuery(criteriaQuery);
			List<Pedido> pedidos = typedQuery.getResultList();

			if (pedidos != null) {
				List<PedidoVo> pedidoVos = new ArrayList<PedidoVo>(pedidos
						.size());

				for (Pedido pedido : pedidos) {
					pedidoVos.add(pedido.converterParaVo());

				}

				result = new PesquisarPedidosResult(pedidoVos);
			}
		} catch (Exception e) {
			Log.warn("Erro ao pesquisar pedidos", e);

			throw new ActionException(e);
		}

		return result;
	}

	@Override
	public void undo(PesquisarPedidosAction action,
			PesquisarPedidosResult result, ExecutionContext context)
			throws ActionException {
	}

	@Override
	public Class<PesquisarPedidosAction> getActionType() {
		return PesquisarPedidosAction.class;
	}

}
