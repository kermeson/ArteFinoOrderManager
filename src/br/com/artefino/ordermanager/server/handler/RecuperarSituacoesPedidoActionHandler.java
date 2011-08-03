package br.com.artefino.ordermanager.server.handler;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.artefino.ordermanager.server.entities.SituacaoPedido;
import br.com.artefino.ordermanager.server.util.JPAUtil;
import br.com.artefino.ordermanager.shared.action.RecuperarSituacoesPedidoAction;
import br.com.artefino.ordermanager.shared.action.RecuperarSituacoesPedidoResult;
import br.com.artefino.ordermanager.shared.vo.SituacaoPedidoVo;

import com.allen_sauer.gwt.log.client.Log;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class RecuperarSituacoesPedidoActionHandler implements
		ActionHandler<RecuperarSituacoesPedidoAction, RecuperarSituacoesPedidoResult> {

	@Inject
	public RecuperarSituacoesPedidoActionHandler() {
	}

	@Override
	public RecuperarSituacoesPedidoResult execute(RecuperarSituacoesPedidoAction action,
			ExecutionContext context) throws ActionException {

		RecuperarSituacoesPedidoResult result = null;

		try {

			EntityManager em = JPAUtil.getEntityManager();
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<SituacaoPedido> criteriaQuery = criteriaBuilder
					.createQuery(SituacaoPedido.class);
			Root<SituacaoPedido> root = criteriaQuery.from(SituacaoPedido.class);
			criteriaQuery.select(root);
			TypedQuery<SituacaoPedido> typedQuery = em.createQuery(criteriaQuery);
			List<SituacaoPedido> situacaoPedidos = typedQuery.getResultList();

			if (situacaoPedidos != null) {
				List<SituacaoPedidoVo> situacaoPedidoVos = new ArrayList<SituacaoPedidoVo>(situacaoPedidos
						.size());

				for (SituacaoPedido SituacaoPedido : situacaoPedidos) {
					situacaoPedidoVos.add(SituacaoPedido.converterParaVo());

				}

				result = new RecuperarSituacoesPedidoResult(situacaoPedidoVos);
			}
		} catch (Exception e) {
			Log.warn("Erro ao pesquisar SituacaoPedidos", e);

			throw new ActionException(e);
		}

		return result;
	}

	@Override
	public void undo(RecuperarSituacoesPedidoAction action,
			RecuperarSituacoesPedidoResult result, ExecutionContext context)
			throws ActionException {
	}

	@Override
	public Class<RecuperarSituacoesPedidoAction> getActionType() {
		return RecuperarSituacoesPedidoAction.class;
	}

}
