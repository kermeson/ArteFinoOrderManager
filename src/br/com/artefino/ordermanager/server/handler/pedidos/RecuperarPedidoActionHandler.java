package br.com.artefino.ordermanager.server.handler.pedidos;

import br.com.artefino.ordermanager.server.entities.Pedido;
import br.com.artefino.ordermanager.server.util.JPAUtil;
import br.com.artefino.ordermanager.shared.action.pedidos.RecuperarPedidoAction;
import br.com.artefino.ordermanager.shared.action.pedidos.RecuperarPedidoResult;

import com.allen_sauer.gwt.log.client.Log;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class RecuperarPedidoActionHandler implements
		ActionHandler<RecuperarPedidoAction, RecuperarPedidoResult> {

	@Inject
	public RecuperarPedidoActionHandler() {
	}

	@Override
	public RecuperarPedidoResult execute(RecuperarPedidoAction action,
			ExecutionContext context) throws ActionException {
		RecuperarPedidoResult result = null;
		try {
			Log.info("Recuperando o Pedido: " + action.getIdPedido());
			Pedido Pedido = (Pedido) JPAUtil.findByID(Pedido.class, action
					.getIdPedido());
			result = new RecuperarPedidoResult(Pedido.converterParaVo());
		} catch (Exception e) {
			Log.error("Erro ao recuperar Pedido: " + action.getIdPedido(), e);
			throw new ActionException(e);
		}
		return result;
	}

	@Override
	public void undo(RecuperarPedidoAction action,
			RecuperarPedidoResult result, ExecutionContext context)
			throws ActionException {
	}

	@Override
	public Class<RecuperarPedidoAction> getActionType() {
		return RecuperarPedidoAction.class;
	}
}
