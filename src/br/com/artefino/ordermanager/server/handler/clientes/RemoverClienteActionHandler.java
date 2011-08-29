package br.com.artefino.ordermanager.server.handler.clientes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

import br.com.artefino.ordermanager.server.businessobject.PedidoBO;
import br.com.artefino.ordermanager.server.entities.Cliente;
import br.com.artefino.ordermanager.server.entities.Pedido;
import br.com.artefino.ordermanager.server.util.JPAUtil;
import br.com.artefino.ordermanager.shared.action.clientes.RemoverClienteAction;
import br.com.artefino.ordermanager.shared.action.clientes.RemoverClienteResult;

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
		try {
			// Verifica se o cliente possui pedidos
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("idCliente", action.getIdCliente());
			List<Pedido> pedidos = PedidoBO.pesquisarPedido(parametros);
			if (pedidos != null && !pedidos.isEmpty()) {
				throw new ActionException(
						"Não é possível remover o cliente, pois o mesmo possui pedidos.");
			}

			JPAUtil.remove(Cliente.class, action.getIdCliente());
			result = new RemoverClienteResult();
		} catch (PersistenceException e) {
			throw new ActionException("Não é possível remover o cliente.");
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
