package br.com.artefino.ordermanager.server.handler.pedidos;

import br.com.artefino.ordermanager.server.entities.Pedido;
import br.com.artefino.ordermanager.server.util.JPAUtil;
import br.com.artefino.ordermanager.shared.action.pedidos.AtualizarPedidoAction;
import br.com.artefino.ordermanager.shared.action.pedidos.AtualizarPedidoResult;

import com.allen_sauer.gwt.log.client.Log;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class AtualizarPedidoActionHandler implements
		ActionHandler<AtualizarPedidoAction, AtualizarPedidoResult> {

	@Inject
	public AtualizarPedidoActionHandler() {
	}

	@Override
	public AtualizarPedidoResult execute(AtualizarPedidoAction action,
			ExecutionContext context) throws ActionException {
		AtualizarPedidoResult result = null;		
		try {
			Pedido Pedido = new Pedido(action.getPedidoVo());
			Log.info("Atualizando o Pedido: " + Pedido.getId());
			JPAUtil.update(Pedido);
			Log.info("Pedido atualizado: " + Pedido.getId());
			result = new AtualizarPedidoResult();
		} catch (Exception e) {
			Log.error("Erro ao atualizar Pedido", e);
			throw new ActionException(e);
		}
		return result;
	}

	@Override
	public void undo(AtualizarPedidoAction action,
			AtualizarPedidoResult result, ExecutionContext context)
			throws ActionException {
	}

	@Override
	public Class<AtualizarPedidoAction> getActionType() {
		return AtualizarPedidoAction.class;
	}
}
