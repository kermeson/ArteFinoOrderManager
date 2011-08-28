package br.com.artefino.ordermanager.server.handler.despesas.categorias;

import br.com.artefino.ordermanager.server.entities.CategoriaDespesa;
import br.com.artefino.ordermanager.server.util.JPAUtil;
import br.com.artefino.ordermanager.shared.action.despesas.categorias.RemoverCategoriaDespesaAction;
import br.com.artefino.ordermanager.shared.action.despesas.categorias.RemoverCategoriaDespesaResult;

import com.allen_sauer.gwt.log.client.Log;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class RemoverCategoriaDespesaActionHandler implements
		ActionHandler<RemoverCategoriaDespesaAction, RemoverCategoriaDespesaResult> {

	@Inject
	public RemoverCategoriaDespesaActionHandler() {
	}

	@Override
	public RemoverCategoriaDespesaResult execute(RemoverCategoriaDespesaAction action,
			ExecutionContext context) throws ActionException {

		RemoverCategoriaDespesaResult result = null;

		try {
			JPAUtil.remove(CategoriaDespesa.class, action.getId());
			result = new RemoverCategoriaDespesaResult();
		} catch (Exception e) {
			Log.error("Erro ao remover despesa", e);
			throw new ActionException(e);
		}
		return result;
	}

	@Override
	public void undo(RemoverCategoriaDespesaAction action, RemoverCategoriaDespesaResult result,
			ExecutionContext context) throws ActionException {
	}

	@Override
	public Class<RemoverCategoriaDespesaAction> getActionType() {
		return RemoverCategoriaDespesaAction.class;
	}
}