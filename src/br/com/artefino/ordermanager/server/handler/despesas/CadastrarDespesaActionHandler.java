package br.com.artefino.ordermanager.server.handler.despesas;

import br.com.artefino.ordermanager.server.entities.Despesa;
import br.com.artefino.ordermanager.server.util.JPAUtil;
import br.com.artefino.ordermanager.shared.action.despesas.CadastrarDespesaAction;
import br.com.artefino.ordermanager.shared.action.despesas.CadastrarDespesaResult;

import com.allen_sauer.gwt.log.client.Log;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class CadastrarDespesaActionHandler implements
		ActionHandler<CadastrarDespesaAction, CadastrarDespesaResult> {

	@Inject
	public CadastrarDespesaActionHandler() {
	}

	@Override
	public CadastrarDespesaResult execute(CadastrarDespesaAction action,
			ExecutionContext context) throws ActionException {

		CadastrarDespesaResult result = null;
		try {
			Despesa despesa = new Despesa(action.getDespesa());
			JPAUtil.save(despesa);
			result = new CadastrarDespesaResult(despesa.getId());
		} catch (Exception e) {
			Log.error("Erro ao salvar despesa", e);
			throw new ActionException(e);
		}

		return result;
	}

	@Override
	public void undo(CadastrarDespesaAction action,
			CadastrarDespesaResult result, ExecutionContext context)
			throws ActionException {
	}

	@Override
	public Class<CadastrarDespesaAction> getActionType() {
		return CadastrarDespesaAction.class;
	}
}
