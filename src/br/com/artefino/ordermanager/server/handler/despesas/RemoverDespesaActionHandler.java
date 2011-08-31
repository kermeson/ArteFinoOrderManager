package br.com.artefino.ordermanager.server.handler.despesas;

import br.com.artefino.ordermanager.server.entities.Despesa;
import br.com.artefino.ordermanager.server.util.JPAUtil;
import br.com.artefino.ordermanager.shared.action.despesas.RemoverDespesaAction;
import br.com.artefino.ordermanager.shared.action.despesas.RemoverDespesaResult;

import com.allen_sauer.gwt.log.client.Log;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class RemoverDespesaActionHandler implements
		ActionHandler<RemoverDespesaAction, RemoverDespesaResult> {

	@Inject
	public RemoverDespesaActionHandler() {
	}

	@Override
	public RemoverDespesaResult execute(RemoverDespesaAction action,
			ExecutionContext context) throws ActionException {

		RemoverDespesaResult result = null;

		try {		
			JPAUtil.remove(Despesa.class, action.getId());
			result = new RemoverDespesaResult();
		} catch (Exception e) {
			Log.error("Erro ao remover categoria", e);
			throw new ActionException(e);
		}
		return result;
	}

	@Override
	public void undo(RemoverDespesaAction action, RemoverDespesaResult result,
			ExecutionContext context) throws ActionException {
	}

	@Override
	public Class<RemoverDespesaAction> getActionType() {
		return RemoverDespesaAction.class;
	}
}
