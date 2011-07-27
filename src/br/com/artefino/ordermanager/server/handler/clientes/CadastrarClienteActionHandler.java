package br.com.artefino.ordermanager.server.handler.clientes;

import br.com.artefino.ordermanager.server.entities.Cliente;
import br.com.artefino.ordermanager.server.util.JPAUtil;
import br.com.artefino.ordermanager.shared.action.clientes.CadastrarClienteAction;
import br.com.artefino.ordermanager.shared.action.clientes.CadastrarClienteResult;

import com.allen_sauer.gwt.log.client.Log;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class CadastrarClienteActionHandler implements
		ActionHandler<CadastrarClienteAction, CadastrarClienteResult> {

	@Inject
	public CadastrarClienteActionHandler() {
	}

	@Override
	public CadastrarClienteResult execute(CadastrarClienteAction action,
			ExecutionContext context) throws ActionException {

		CadastrarClienteResult result = null;

		// DOMConfigurator.configure("log4j.xml");

		Log.info("Cadastrando o cliente");

		try {

			Cliente cliente = new Cliente(action.getClienteVo());

			JPAUtil.save(cliente);

			Log.info("Cliente cadastrado" + cliente.getId());

			result = new CadastrarClienteResult(cliente.getId());
		} catch (Exception e) {
			Log.error("Erro ao salvar cliente", e);
			throw new ActionException(e);
		}

		return result;
	}

	@Override
	public void undo(CadastrarClienteAction action, CadastrarClienteResult result,
			ExecutionContext context) throws ActionException {
	}

	@Override
	public Class<CadastrarClienteAction> getActionType() {
		return CadastrarClienteAction.class;
	}
}
