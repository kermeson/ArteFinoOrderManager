package br.com.artefino.ordermanager.server.handler.despesas.categorias;

import br.com.artefino.ordermanager.server.entities.CategoriaDespesa;
import br.com.artefino.ordermanager.server.util.JPAUtil;
import br.com.artefino.ordermanager.shared.action.despesas.categorias.CadastrarCategoriaDespesaAction;
import br.com.artefino.ordermanager.shared.action.despesas.categorias.CadastrarCategoriaDespesaResult;

import com.allen_sauer.gwt.log.client.Log;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class CadastrarCategoriaDespesaActionHandler implements
		ActionHandler<CadastrarCategoriaDespesaAction, CadastrarCategoriaDespesaResult> {

	@Inject
	public CadastrarCategoriaDespesaActionHandler() {
	}

	@Override
	public CadastrarCategoriaDespesaResult execute(CadastrarCategoriaDespesaAction action,
			ExecutionContext context) throws ActionException {

		CadastrarCategoriaDespesaResult result = null;
		try {
			CategoriaDespesa categoria = new CategoriaDespesa(action.getCategoria());
			JPAUtil.save(categoria);
			result = new CadastrarCategoriaDespesaResult(categoria.getId());
		} catch (Exception e) {
			Log.error("Erro ao salvar categoria de despesa", e);
			throw new ActionException(e);
		}

		return result;
	}

	@Override
	public void undo(CadastrarCategoriaDespesaAction action,
			CadastrarCategoriaDespesaResult result, ExecutionContext context)
			throws ActionException {
	}

	@Override
	public Class<CadastrarCategoriaDespesaAction> getActionType() {
		return CadastrarCategoriaDespesaAction.class;
	}
}
