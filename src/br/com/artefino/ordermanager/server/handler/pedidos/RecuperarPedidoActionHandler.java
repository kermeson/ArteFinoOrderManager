package br.com.artefino.ordermanager.server.handler.pedidos;

import br.com.artefino.ordermanager.server.entities.Cliente;
import br.com.artefino.ordermanager.server.util.JPAUtil;
import br.com.artefino.ordermanager.shared.action.clientes.RecuperarClienteAction;
import br.com.artefino.ordermanager.shared.action.clientes.RecuperarClienteResult;

import com.allen_sauer.gwt.log.client.Log;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class RecuperarPedidoActionHandler implements
		ActionHandler<RecuperarClienteAction, RecuperarClienteResult> {

	@Inject
	public RecuperarPedidoActionHandler() {
	}

	@Override
	public RecuperarClienteResult execute(RecuperarClienteAction action,
			ExecutionContext context) throws ActionException {
		RecuperarClienteResult result = null;
		try {
			Log.info("Recuperando o cliente: " + action.getIdCliente());
			Cliente cliente = (Cliente) JPAUtil.findByID(Cliente.class, action
					.getIdCliente());
			result = new RecuperarClienteResult(cliente.converterParaVo());
		} catch (Exception e) {
			Log.error("Erro ao recuperar cliente: " + action.getIdCliente(), e);
			throw new ActionException(e);
		}
		return result;
	}

	@Override
	public void undo(RecuperarClienteAction action,
			RecuperarClienteResult result, ExecutionContext context)
			throws ActionException {
	}

	@Override
	public Class<RecuperarClienteAction> getActionType() {
		return RecuperarClienteAction.class;
	}
}
