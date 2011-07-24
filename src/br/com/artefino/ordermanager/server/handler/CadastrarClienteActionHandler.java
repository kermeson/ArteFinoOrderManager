package br.com.artefino.ordermanager.server.handler;

import br.com.artefino.ordermanager.server.entities.Cliente;
import br.com.artefino.ordermanager.server.util.JPAUtil;
import br.com.artefino.ordermanager.shared.action.clientes.CadastrarCliente;
import br.com.artefino.ordermanager.shared.action.clientes.CadastrarClienteResult;

import com.allen_sauer.gwt.log.client.Log;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class CadastrarClienteActionHandler implements
		ActionHandler<CadastrarCliente, CadastrarClienteResult> {

	@Inject
	public CadastrarClienteActionHandler() {
	}

	@Override
	public CadastrarClienteResult execute(CadastrarCliente action,
			ExecutionContext context) throws ActionException {

		CadastrarClienteResult result = null;

		// DOMConfigurator.configure("log4j.xml");

		Log.info("Cadastrando o cliente");

		try {

			Cliente cliente = new Cliente(action.getClienteVo());

			JPAUtil.startTransaction();
			JPAUtil.persist(cliente);
			JPAUtil.endTransaction(true);

			Log.info("Cliente cadastrado" + cliente.getId());

			result = new CadastrarClienteResult(cliente.getId());
		} catch (Exception e) {
			Log.error("Unable to create Account", e);

			throw new ActionException(e);
		}

		return result;
	}

	@Override
	public void undo(CadastrarCliente action, CadastrarClienteResult result,
			ExecutionContext context) throws ActionException {
	}

	@Override
	public Class<CadastrarCliente> getActionType() {
		return CadastrarCliente.class;
	}
}
