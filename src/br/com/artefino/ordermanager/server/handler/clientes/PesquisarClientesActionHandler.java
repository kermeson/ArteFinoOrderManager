package br.com.artefino.ordermanager.server.handler.clientes;

import java.util.ArrayList;
import java.util.List;

import br.com.artefino.ordermanager.server.businessobject.ClienteBO;
import br.com.artefino.ordermanager.server.entities.Cliente;
import br.com.artefino.ordermanager.shared.action.clientes.PesquisarClientesAction;
import br.com.artefino.ordermanager.shared.action.clientes.PesquisarClientesResult;
import br.com.artefino.ordermanager.shared.vo.ClienteVo;

import com.allen_sauer.gwt.log.client.Log;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class PesquisarClientesActionHandler implements
		ActionHandler<PesquisarClientesAction, PesquisarClientesResult> {

	@Inject
	public PesquisarClientesActionHandler() {
	}

	@Override
	public PesquisarClientesResult execute(PesquisarClientesAction action,
			ExecutionContext context) throws ActionException {

		PesquisarClientesResult result = null;

		try {

			List<Cliente> clientes = ClienteBO.pesquisarClientes(action
					.getParametros());

			if (clientes != null) {
				List<ClienteVo> clienteVos = new ArrayList<ClienteVo>(
						clientes.size());

				for (Cliente cliente : clientes) {
					clienteVos.add(cliente.converterParaVo());

				}

				result = new PesquisarClientesResult(clienteVos);
			}
		} catch (Exception e) {
			Log.warn("Erro ao pesquisar clientes", e);

			throw new ActionException(e);
		}

		return result;
	}

	@Override
	public void undo(PesquisarClientesAction action,
			PesquisarClientesResult result, ExecutionContext context)
			throws ActionException {
	}

	@Override
	public Class<PesquisarClientesAction> getActionType() {
		return PesquisarClientesAction.class;
	}

}
