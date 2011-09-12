package br.com.artefino.ordermanager.server.handler.pedidos;

import java.util.ArrayList;
import java.util.List;

import br.com.artefino.ordermanager.server.businessobject.PedidoBO;
import br.com.artefino.ordermanager.server.entities.Pedido;
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
			Long totalPedidos = PedidoBO.retornarTotalPedidos(action
					.getParametros());
			List<PedidoVo> pedidoVos = new ArrayList<PedidoVo>();

			if (totalPedidos != null && totalPedidos.longValue() > 0) {
				List<Pedido> pedidos = PedidoBO.pesquisarPedidos(action
						.getParametros(), action.getMaxResults(), action
						.getFirstResult());
				if (pedidos != null) {
					for (Pedido pedido : pedidos) {
						pedidoVos.add(pedido.converterParaVo());

					}
				}
			}

			result = new PesquisarPedidosResult(pedidoVos, totalPedidos);

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
