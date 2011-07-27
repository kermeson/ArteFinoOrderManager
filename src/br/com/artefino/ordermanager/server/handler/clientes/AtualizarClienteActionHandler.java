package br.com.artefino.ordermanager.server.handler.clientes;

import br.com.artefino.ordermanager.server.entities.Cliente;
import br.com.artefino.ordermanager.server.util.JPAUtil;
import br.com.artefino.ordermanager.shared.action.clientes.AtualizarClienteAction;
import br.com.artefino.ordermanager.shared.action.clientes.AtualizarClienteResult;

import com.allen_sauer.gwt.log.client.Log;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class AtualizarClienteActionHandler implements
		ActionHandler<AtualizarClienteAction, AtualizarClienteResult> {

	@Inject
	public AtualizarClienteActionHandler() {
	}

	@Override
	public AtualizarClienteResult execute(AtualizarClienteAction action,
			ExecutionContext context) throws ActionException {
		AtualizarClienteResult result = null;		
		try {
			Cliente cliente = new Cliente(action.getClienteVo());
			Log.info("Atualizando o cliente: " + cliente.getId());
			JPAUtil.update(cliente);
			Log.info("Cliente atualizado: " + cliente.getId());
			result = new AtualizarClienteResult();
		} catch (Exception e) {
			Log.error("Erro ao atualizar cliente", e);
			throw new ActionException(e);
		}
		return result;
	}

	@Override
	public void undo(AtualizarClienteAction action,
			AtualizarClienteResult result, ExecutionContext context)
			throws ActionException {
	}

	@Override
	public Class<AtualizarClienteAction> getActionType() {
		return AtualizarClienteAction.class;
	}
}
