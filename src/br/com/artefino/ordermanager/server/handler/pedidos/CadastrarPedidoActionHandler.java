package br.com.artefino.ordermanager.server.handler.pedidos;

import java.util.Date;

import br.com.artefino.ordermanager.server.entities.Pedido;
import br.com.artefino.ordermanager.server.util.JPAUtil;
import br.com.artefino.ordermanager.shared.action.pedidos.CadastrarPedidoAction;
import br.com.artefino.ordermanager.shared.action.pedidos.CadastrarPedidoResult;

import com.allen_sauer.gwt.log.client.Log;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class CadastrarPedidoActionHandler implements
		ActionHandler<CadastrarPedidoAction, CadastrarPedidoResult> {

	@Inject
	public CadastrarPedidoActionHandler() {
	}

	@Override
	public CadastrarPedidoResult execute(CadastrarPedidoAction action,
			ExecutionContext context) throws ActionException {

		CadastrarPedidoResult result = null;

		Log.info("Cadastrando o pedido");

		try {

			Pedido pedido = new Pedido(action.getPedidoVo());
			pedido.setDataCadastro(new Date());

			JPAUtil.save(pedido);

			Log.info("Pedido cadastrado" + pedido.getId());

			result = new CadastrarPedidoResult(pedido.getId());
		} catch (Exception e) {
			Log.error("Erro ao salvar pedido", e);
			throw new ActionException(e);
		}

		return result;
	}

	@Override
	public void undo(CadastrarPedidoAction action, CadastrarPedidoResult result,
			ExecutionContext context) throws ActionException {
	}

	@Override
	public Class<CadastrarPedidoAction> getActionType() {
		return CadastrarPedidoAction.class;
	}
}
