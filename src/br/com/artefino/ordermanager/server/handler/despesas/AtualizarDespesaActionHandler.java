package br.com.artefino.ordermanager.server.handler.despesas;

import br.com.artefino.ordermanager.server.entities.Despesa;
import br.com.artefino.ordermanager.server.util.JPAUtil;
import br.com.artefino.ordermanager.shared.action.despesas.AtualizarDespesaAction;
import br.com.artefino.ordermanager.shared.action.despesas.AtualizarDespesaResult;

import com.allen_sauer.gwt.log.client.Log;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class AtualizarDespesaActionHandler implements
		ActionHandler<AtualizarDespesaAction, AtualizarDespesaResult> {

	@Inject
	public AtualizarDespesaActionHandler() {
	}

	@Override
	public AtualizarDespesaResult execute(AtualizarDespesaAction action,
			ExecutionContext context) throws ActionException {
		AtualizarDespesaResult result = null;
		try {
			Despesa cliente = new Despesa(action.getDespesa());
			JPAUtil.update(cliente);
			result = new AtualizarDespesaResult();
		} catch (Exception e) {
			Log.error("Erro ao atualizar despesa", e);
			throw new ActionException(e);
		}
		return result;
	}

	@Override
	public void undo(AtualizarDespesaAction action,
			AtualizarDespesaResult result, ExecutionContext context)
			throws ActionException {
	}

	@Override
	public Class<AtualizarDespesaAction> getActionType() {
		return AtualizarDespesaAction.class;
	}
}
