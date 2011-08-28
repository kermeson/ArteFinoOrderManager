package br.com.artefino.ordermanager.server.handler.despesas;

import br.com.artefino.ordermanager.server.entities.Despesa;
import br.com.artefino.ordermanager.server.util.JPAUtil;
import br.com.artefino.ordermanager.shared.action.despesas.RecuperarDespesaAction;
import br.com.artefino.ordermanager.shared.action.despesas.RecuperarDespesaResult;

import com.allen_sauer.gwt.log.client.Log;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class RecuperarDespesaActionHandler implements
		ActionHandler<RecuperarDespesaAction, RecuperarDespesaResult> {

	@Inject
	public RecuperarDespesaActionHandler() {
	}

	@Override
	public RecuperarDespesaResult execute(RecuperarDespesaAction action,
			ExecutionContext context) throws ActionException {
		RecuperarDespesaResult result = null;
		try {
			Despesa cliente = (Despesa) JPAUtil.findByID(Despesa.class, action
					.getId());
			result = new RecuperarDespesaResult(cliente.converterParaVo());
		} catch (Exception e) {
			Log.error("Erro ao recuperar cliente", e);
			throw new ActionException(e);
		}
		return result;
	}

	@Override
	public void undo(RecuperarDespesaAction action,
			RecuperarDespesaResult result, ExecutionContext context)
			throws ActionException {
	}

	@Override
	public Class<RecuperarDespesaAction> getActionType() {
		return RecuperarDespesaAction.class;
	}
}
