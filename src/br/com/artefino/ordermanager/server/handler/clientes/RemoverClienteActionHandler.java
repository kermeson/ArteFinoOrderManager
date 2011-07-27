package br.com.artefino.ordermanager.server.handler.clientes;

import br.com.artefino.ordermanager.server.entities.Cliente;
import br.com.artefino.ordermanager.server.util.JPAUtil;
import br.com.artefino.ordermanager.shared.action.clientes.RemoverClienteAction;
import br.com.artefino.ordermanager.shared.action.clientes.RemoverClienteResult;

import com.allen_sauer.gwt.log.client.Log;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class RemoverClienteActionHandler implements
		ActionHandler<RemoverClienteAction, RemoverClienteResult> {

	@Inject
	public RemoverClienteActionHandler() {
	}

	@Override
	public RemoverClienteResult execute(RemoverClienteAction action,
			ExecutionContext context) throws ActionException {

		RemoverClienteResult result = null;

		// DOMConfigurator.configure("log4j.xml");

		Log.info("Cadastrando o cliente");

		try {

			JPAUtil.remove(Cliente.class, action.getIdCliente());

			Log.info("Cliente removido" + action.getIdCliente());

			result = new RemoverClienteResult();
		} catch (Exception e) {
			Log.error("Erro ao salvar cliente", e);
			throw new ActionException(e);
		}

		return result;
	}

	@Override
	public void undo(RemoverClienteAction action, RemoverClienteResult result,
			ExecutionContext context) throws ActionException {
	}

	@Override
	public Class<RemoverClienteAction> getActionType() {
		return RemoverClienteAction.class;
	}
}
